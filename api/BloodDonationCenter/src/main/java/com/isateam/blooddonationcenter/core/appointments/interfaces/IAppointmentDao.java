package com.isateam.blooddonationcenter.core.appointments.interfaces;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentDao extends JpaRepository<Appointment, Long> {


    @Query(value="select * from appointments where date(startTime)=?1 and center_id=?2", nativeQuery = true)
    List<Appointment> getAllByCenterAndDate(LocalDate dt, long centerId);

    @Query(value="select * from appointments where date(startTime)=?1 and center_id=?2 and state=1", nativeQuery = true)
    List<Appointment> getAllFreeByCenterAndDate(LocalDate dt, long centerId);

}
