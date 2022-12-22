package com.isateam.blooddonationcenter.core.appointments.interfaces;

import com.isateam.blooddonationcenter.core.appointments.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment create(Appointment appointment);

    Appointment findById(long id);

    void reserve(long id, long userId);
    List<Appointment> getAllFreeByDateAndCenter(LocalDate date, long centerId);
    List<Appointment> getAllByDateAndCenter(LocalDate date, long centerId);
    List<Appointment> getAllFreeByDateTime(LocalDateTime date);
}
