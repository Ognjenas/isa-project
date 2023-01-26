package com.isateam.blooddonationcenter.core.appointments.interfaces;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.appointments.dtos.AppointmentsForShowDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment create(Appointment appointment);
    Appointment findById(long id);
    void reserve(long id, long userId);
    List<Appointment> getAllFreeByDateAndCenter(LocalDate date, long centerId);
    List<Appointment> getAllByDateAndCenter(LocalDate date, long centerId);

    List<Appointment> getAllFreeByDateTime(LocalDateTime date, String order);
    List<Appointment> getAllFutureAppointments(long centerId, String orderBy);

    List<Appointment> getAllFutureAppointmentsByUser(long userId);
    void cancel(long appointmentId, long userId);

    List<AppointmentsForShowDto> getAllAppointmentsForCenter(long centerId);

    List<Appointment> getUserDonationHistory(long userId, long workerId);

    List<Appointment> getAllUsersPastAppointments(long userId, String orderBy);
}
