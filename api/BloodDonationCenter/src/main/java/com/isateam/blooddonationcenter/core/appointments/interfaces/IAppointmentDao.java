package com.isateam.blooddonationcenter.core.appointments.interfaces;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.appointments.AppointmentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentDao extends PagingAndSortingRepository<Appointment, Long> {


    @Query(value="select id, start_time, duration, user_id, center_id, state  from appointments where date(start_time)=?1 and center_id=?2", nativeQuery = true)
    List<Appointment> getAllByCenterAndDate(LocalDate dt, long centerId);

    @Query(value="select id, start_time, duration, user_id, center_id, state  from appointments natural join centers where date(start_time)=?1 and center_id=?2 and state=0 order by average_grade ASC", nativeQuery = true)
    List<Appointment> getAllFreeByCenterAndDateAsc(LocalDate dt, long centerId);

    @Query(value="select id, start_time, duration, user_id, center_id, state  from appointments natural join centers where date(start_time)=?1 and center_id=?2 and state=0 order by average_grade DESC", nativeQuery = true)
    List<Appointment> getAllFreeByCenterAndDateDesc(LocalDate dt, long centerId);
    List<Appointment> findAllByStartTimeAndStateOrderByCenter_AverageGradeAsc(LocalDateTime date, AppointmentState state);
    List<Appointment> findAllByStartTimeAndStateOrderByCenter_AverageGradeDesc(LocalDateTime date, AppointmentState state);
    List<Appointment> getAllByStartTimeAfterAndStateIsAndCenterIdOrderByStartTimeAsc(LocalDateTime from, AppointmentState state, long id);
    List<Appointment> getAllByStartTimeAfterAndStateIsAndCenterIdOrderByStartTimeDesc(LocalDateTime from, AppointmentState state, long id);

    List<Appointment> findAllByUserIdAndStartTimeIsAfter(long userId, LocalDateTime startTime);
    List<Appointment> findAllByCenter_Id(long centerId);
    @Query(value = "SELECT * FROM appointments WHERE (startTime BETWEEN ?1 AND ?2) OR (endTime BETWEEN ?1 AND ?2) OR (startTime < :?1 AND endTime > ?2)",nativeQuery = true)
    List<Appointment> findOverlapping(LocalDateTime start, LocalDateTime end);


}
