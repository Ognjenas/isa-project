package com.isateam.blooddonationcenter.core.appointments;

import com.isateam.blooddonationcenter.core.appointments.dtos.AppointmentsForShowDto;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentDao;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentLogDao;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentService;
import com.isateam.blooddonationcenter.core.email.EmailDetails;
import com.isateam.blooddonationcenter.core.email.IEmailService;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import com.isateam.blooddonationcenter.core.workers.Worker;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.ArrayList;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {
    private final IAppointmentDao appointmentDao;
    private final IUserEntityDao userEntityDao;
    private final IAppointmentLogDao appointmentLogDao;
    private final IWorkerDao workerDao;

    private final IEmailService emailService;


    @Override
    public Appointment create(Appointment appointment) {
        validateCreation(appointment);
        return appointmentDao.save(appointment);
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

//    private void checkUsersGaveDonations(long userId) {
//        var appointments = appointmentDao.findAllByUserIdAndStartTimeIsAfter(userId,
//                LocalDateTime.now().minusMonths(6));
//        if (!appointments.isEmpty()) {
//            throw new BadRequestException("You already gave blood in last 6 months or already reserver appointment");      }
//        sendReservationEmail(appointment, user);
//    }

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
        System.out.println(order);
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
        List<Appointment> inputList = appointmentDao.findAllByCenter_Id(centerId);
        List<AppointmentsForShowDto> returnList = packShowAppointmentsDto(inputList);
        return returnList;
    }

    private List<AppointmentsForShowDto> packShowAppointmentsDto(List<Appointment> appointments){
        List<AppointmentsForShowDto> showList = new ArrayList<AppointmentsForShowDto>();
        for (Appointment app: appointments){
            AppointmentsForShowDto appointment = new AppointmentsForShowDto();
            if(app.getUser() != null){
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

    private void checkUsersGaveDonations(long userId) {
        var appointments = appointmentDao.findAllByUserIdAndStartTimeIsAfter(userId,
                LocalDateTime.now().minusMonths(6));
        if (!appointments.isEmpty()) {
            throw new BadRequestException("You already gave blood in last 6 months or already reserver appointment");
        }
    }

    private void checkUsersSurvey(long userId) {
        User user = userEntityDao.findById(userId).orElseThrow(() -> new BadRequestException("User does not exist"));
        if (user.getSurveys().isEmpty()) {
            throw new BadRequestException("You must do the survey first");
        }
    }

    private void validateCreation(Appointment appointment) {
        LocalDateTime startTime = appointment.getStartTime();
        LocalDateTime endTime = startTime.plusMinutes(appointment.getDuration());

        List<Appointment> overlapings = appointmentDao.findOverlapping(startTime, endTime);

        if(overlapings.size() > 0)
            throw new BadRequestException("There is an interval overlapping with given start time and duration!");
    }

}
