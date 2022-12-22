package com.isateam.blooddonationcenter.core.appointments.interfaces;

import com.isateam.blooddonationcenter.core.appointments.AppointmentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAppointmentLogDao extends JpaRepository<AppointmentLog, Long> {
    List<AppointmentLog> findAllByAppointmentId(long appointmentId);
}
