package com.isateam.blooddonationcenter.core.appointments;

import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentDao;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentService;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public void reserveAppointment(long id) {
        Appointment app = findById(id);
        if(app.getState() != AppointmentState.FREE)
            throw new BadRequestException("Appointment is not free!");
        app.setState(AppointmentState.TAKEN);
    }

    @Override
    public List<Appointment> getAllFreeByDateAndCenter(LocalDate date, long centerId) {
        return appointmentDao.getAllFreeByCenterAndDate(date, centerId);
    }

    @Override
    public List<Appointment> getAllByDateAndCenter(LocalDate date, long centerId) {
        return appointmentDao.getAllByCenterAndDate(date, centerId);
    }
}
