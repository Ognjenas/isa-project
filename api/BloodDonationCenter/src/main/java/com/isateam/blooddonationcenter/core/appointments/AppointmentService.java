package com.isateam.blooddonationcenter.core.appointments;

import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentDao;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentService;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {
    private final IAppointmentDao appointmentDao;


    @Override
    public Appointment create(Appointment appointment) {
        return appointmentDao.save(appointment);
    }

    @Override
    public Appointment findById(long id) {
        return appointmentDao.findById(id).orElseThrow(
                () ->  new NotFoundException("Appointment not found!")
        );
    }

    @Override
    public void reserve(long id, long userId) {
        Appointment appointment = findById(id);
        if(appointment.getState() != AppointmentState.FREE)
            throw new BadRequestException("Appointment is not free!");
        appointment.setState(AppointmentState.TAKEN);
        appointment.setUser(User.builder().id(userId).build());
        appointmentDao.save(appointment);
    }

    @Override
    public List<Appointment> getAllFreeByDateAndCenter(LocalDate date, long centerId) {
        return appointmentDao.getAllFreeByCenterAndDate(date, centerId);
    }

    @Override
    public List<Appointment> getAllByDateAndCenter(LocalDate date, long centerId) {
        return appointmentDao.getAllByCenterAndDate(date, centerId);
    }

    @Override
    public List<Appointment> getAllFreeByDateTime(LocalDateTime date) {
        return appointmentDao.findAllByStartTimeAndStateEquals(date, AppointmentState.FREE);
    }


}
