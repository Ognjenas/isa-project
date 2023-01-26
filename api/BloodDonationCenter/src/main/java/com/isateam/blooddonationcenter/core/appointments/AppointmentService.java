package com.isateam.blooddonationcenter.core.appointments;

import com.isateam.blooddonationcenter.core.appointments.dtos.AppointmentsForShowDto;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentDao;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentLogDao;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentService;
import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.centers.interfaces.CenterDao;
import com.isateam.blooddonationcenter.core.email.EmailDetails;
import com.isateam.blooddonationcenter.core.email.IEmailService;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.surveys.Survey;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import com.isateam.blooddonationcenter.core.workers.Worker;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {
    private final IAppointmentDao appointmentDao;
    private final IUserEntityDao userEntityDao;
    private final IAppointmentLogDao appointmentLogDao;
    private final IWorkerDao workerDao;
    private final CenterDao centerDao;

    private final IEmailService emailService;


    @Override
    @Transactional
    public Appointment create(Appointment appointment) {
        Center center = centerDao.findById(appointment.getCenter().getId()).orElseThrow();
        center.setLocked(true);
        centerDao.save(center);
        validateCreation(appointment);
        var returnValue = appointmentDao.save(appointment);
        center.setLocked(false);
        centerDao.save(center);
        return returnValue;
    }

    @Override
    public Appointment findById(long id) {
        return appointmentDao.findById(id).orElseThrow(
                () -> new NotFoundException("Appointment not found!")
        );
    }

    @Override
    public void reserve(long id, long userId) {
        checkUsersSurvey(userId);
        checkIfAlreadyReservedInCenter(id, userId);
        checkUsersGaveDonations(userId);
        User user = userEntityDao.findById(userId)
                .orElseThrow(() -> new NotFoundException("User doesn't exist!"));

        Appointment appointment = findById(id);
        if (appointment.getState() != AppointmentState.FREE)
            throw new BadRequestException("Appointment is not free!");

        appointment.setState(AppointmentState.TAKEN);
        appointment.setUser(user);
        appointmentDao.save(appointment);
        sendReservationEmail(appointment, user);
        saveAppointmentLog(userId, appointment);
    }

    private void checkIfAlreadyReservedInCenter(long id, long userId) {
        List<AppointmentLog> logs = appointmentLogDao.findAllByAppointmentId(id);
        if(logs.stream().anyMatch(a -> a.getUser().getId().equals(userId))) {
            throw new BadRequestException("You already made this appointment before");
        }
    }

    @Override
    public void cancel(long appointmentId, long userId) {
        User user = userEntityDao.findById(userId).orElseThrow();
        Appointment appointment = appointmentDao.findById(appointmentId).orElseThrow(() ->
                new BadRequestException("Appointment does not exist"));
        if (!appointment.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Cannot do that");
        }

        if(appointment.getStartTime().minusHours(24).isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Cannot cancel if less than 24h");
        }

        appointment.setState(AppointmentState.FREE);
        appointment.setUser(null);
        appointmentDao.save(appointment);
    }

    private void saveAppointmentLog(long userId, Appointment appointment) {
        AppointmentLog appointmentLog = AppointmentLog.builder()
                .user(User.builder().id(userId).build())
                .appointment(appointment)
                .time(LocalDateTime.now())
                .build();
        appointmentLogDao.save(appointmentLog);
    }


    private void checkUsersGaveDonations(long userId) {
        var appointments = appointmentDao.findAllByUserIdAndStartTimeIsAfter(userId,
                LocalDateTime.now().minusMonths(6));
        if (!appointments.isEmpty()) {
            throw new BadRequestException("You already gave blood in last 6 months or already reserver appointment");
        }

    }

    private void checkUsersSurvey(long userId) {
        User user = userEntityDao.findById(userId).orElseThrow(() -> new BadRequestException("User does not exist"));
        Set<Survey> surveys= user.getSurveys();
        if(surveys.isEmpty() || surveys.stream().allMatch(Survey::isUsed)){
            throw new BadRequestException("You must do the survey first");
        }
    }

    @Override
    public List<Appointment> getAllFreeByDateAndCenter(LocalDate date, long centerId) {
        return appointmentDao.getAllFreeByCenterAndDateAsc(date, centerId);
    }

    @Override
    public List<Appointment> getAllByDateAndCenter(LocalDate date, long centerId) {
        return appointmentDao.getAllByCenterAndDate(date, centerId);
    }

    @Override
    public List<Appointment> getAllFreeByDateTime(LocalDateTime date, String order) {
        if(order.equalsIgnoreCase("asc"))
            return appointmentDao.findAllByStartTimeAndStateOrderByCenter_AverageGradeAsc(date, AppointmentState.FREE);
        return appointmentDao.findAllByStartTimeAndStateOrderByCenter_AverageGradeDesc(date, AppointmentState.FREE);
    }

    @Override
    public List<Appointment> getAllFutureAppointments(long centerId, String orderBy) {
        return orderBy.equals("asc") ? appointmentDao
                .getAllByStartTimeAfterAndStateIsAndCenterIdOrderByStartTimeAsc(LocalDateTime.now(),
                        AppointmentState.FREE, centerId) :
                appointmentDao.getAllByStartTimeAfterAndStateIsAndCenterIdOrderByStartTimeDesc(LocalDateTime.now(),
                        AppointmentState.FREE, centerId);
    }

    @Override
    public List<Appointment> getAllFutureAppointmentsByUser(long userId) {
        return appointmentDao.findAllByUserIdAndStartTimeIsAfter(userId, LocalDateTime.now());
    }

    @Override
    public List<AppointmentsForShowDto> getAllAppointmentsForCenter(long userId) {
        Worker worker = workerDao.findByUser_Id(userId);
        long centerId = worker.getCenter().getId();
        System.out.println(centerId);
        List<Appointment> inputList = appointmentDao.findAllByCenter_Id(centerId);
        List<AppointmentsForShowDto> returnList = packShowAppointmentsDto(inputList);
        return returnList;
    }

    @Override
    public List<Appointment> getUserDonationHistory(long userId, long workerId) {
        Worker worker = workerDao.findById(workerId).orElseThrow();
        long centerId = worker.getCenter().getId();
        return appointmentDao.getUserDonationHistory(userId, centerId);
    }

    @Override
<<<<<<< HEAD
    public List<Appointment> getAllUsersPastAppointments(long userId, String orderBy) {
        if (orderBy.equals("asc")) {
            return appointmentDao.findAllByUser_IdAndStateAndStartTimeBeforeOrderByStartTimeAsc(userId,
                    AppointmentState.TAKEN, LocalDateTime.now());
        }
        return appointmentDao.findAllByUser_IdAndStateAndStartTimeBeforeOrderByStartTimeDesc(userId,
                AppointmentState.TAKEN, LocalDateTime.now());
    }

=======
>>>>>>> f46dba1 (Patient can make appointment for self and worker can open appointment outside calendar by id)
    @Transactional
    public Appointment createForSelf(Appointment appointment) {
        checkUsersSurvey(appointment.getUser().getId());
        checkUsersGaveDonations(appointment.getUser().getId());
        Center center = centerDao.findById(appointment.getCenter().getId()).orElseThrow();
        center.setLocked(true);
        centerDao.save(center);
        validateCreation(appointment);
        var returnValue = appointmentDao.save(appointment);
        center.setLocked(false);
        centerDao.save(center);
        sendReservationEmail(appointment, appointment.getUser());
        saveAppointmentLog(appointment.getUser().getId(), appointment);
        return returnValue;
    }

    public boolean checkAppointment(long appointmentId){
        Optional<Appointment> appointment = appointmentDao.findById(appointmentId);
        if(appointment.isEmpty()){
            throw new BadRequestException("The appointment doesnt exist");
        }else{
            if(appointment.get().getState().equals(AppointmentState.FINISHED)){
                throw new BadRequestException("The appointment is finished");
            } else if (appointment.get().getState().equals(AppointmentState.FREE)) {
                throw new BadRequestException("The appointment isn't taken");
            }else{
                checkUsersSurvey(appointment.get().getUser().getId());
            }
        }
        return true;
    }

    private List<AppointmentsForShowDto> packShowAppointmentsDto(List<Appointment> appointments){
        List<AppointmentsForShowDto> showList = new ArrayList<AppointmentsForShowDto>();
        for (Appointment app: appointments){
            AppointmentsForShowDto appointment = new AppointmentsForShowDto();
            if(app.getUser() != null){
                appointment.setId(app.getId());
                appointment.setTitle(app.getUser().getName()+" "+app.getUser().getSurname());
                appointment.setStart(app.getStartTime());
                appointment.setEnd(app.getStartTime().plusMinutes(app.getDuration()));
                appointment.setAllDay(false);
                showList.add(appointment);
            }
        }
        return showList;
    }
    private void sendReservationEmail(Appointment appointment, User user) {
        String content = formatReservedEmail(appointment);
        EmailDetails mail = EmailDetails.builder()
                .body(content)
                .recipient(user.getEmail())
                .subject("Appointment reservation")
                .build();
        emailService.sendSimpleMail(mail);
    }

    private String formatReservedEmail(Appointment appointment) {
        return "Your appointment starts at " + appointment.getStartTime().toString();
    }

//    private void checkUsersGaveDonations(long userId) {
//        var appointments = appointmentDao.findAllByUserIdAndStartTimeIsAfter(userId,
//                LocalDateTime.now().minusMonths(6));
//        if (!appointments.isEmpty()) {
//            throw new BadRequestException("You already gave blood in last 6 months or already reserver appointment");
//        }
//    }
//
//    private void checkUsersSurvey(long userId) {
//        User user = userEntityDao.findById(userId).orElseThrow(() -> new BadRequestException("User does not exist"));
//        if (user.getSurveys().isEmpty()) {
//            throw new BadRequestException("You must do the survey first");
//        }
//    }

    private void validateCreation(Appointment appointment) {
        LocalDateTime startTime = appointment.getStartTime();
        LocalDateTime endTime = startTime.plusMinutes(appointment.getDuration());

        List<Appointment> overlapings = appointmentDao.findOverlapping(startTime, endTime, appointment.getCenter().getId());

        if(overlapings.size() > 0)
            throw new BadRequestException("There is an interval overlapping with given start time and duration!");
    }

}
