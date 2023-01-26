package com.isateam.blooddonationcenter.core.appointments.interfaces;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.appointments.AppointmentState;
import com.isateam.blooddonationcenter.core.users.User;
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

    @Query(value="select * from appointments where user_id=?1 and state=2 and center_id=?2", nativeQuery = true)
    List<Appointment> getUserDonationHistory(long userId, long centerId);
    List<Appointment> findAllByUserIdAndStartTimeIsAfter(long userId, LocalDateTime startTime);
    List<Appointment> findAllByCenter_Id(long centerId);
    @Query(value = "SELECT * FROM appointments WHERE center_id = ?3 and ((start_time BETWEEN ?1 AND ?2) OR (end_time BETWEEN ?1 AND ?2) OR (start_time < ?1 AND end_time > ?2))",nativeQuery = true)
    List<Appointment> findOverlapping(LocalDateTime start, LocalDateTime end, long centerId);
    List<Appointment> findAllByUser_IdAndStateAndStartTimeBeforeOrderByStartTimeAsc(long userId, AppointmentState state, LocalDateTime dateTime);
    List<Appointment> findAllByUser_IdAndStateAndStartTimeBeforeOrderByStartTimeDesc(long userId, AppointmentState state, LocalDateTime dateTime);
}
