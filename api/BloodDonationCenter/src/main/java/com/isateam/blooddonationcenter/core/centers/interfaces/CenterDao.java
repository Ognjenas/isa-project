package com.isateam.blooddonationcenter.core.centers.interfaces;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.centers.Center;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CenterDao extends JpaRepository<Center, Long> {
    List<Center> findDistinctByAppointmentsIsNotIn(List<Appointment> appointments);
}
