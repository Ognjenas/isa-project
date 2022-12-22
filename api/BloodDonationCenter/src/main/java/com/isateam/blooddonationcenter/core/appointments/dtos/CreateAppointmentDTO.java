package com.isateam.blooddonationcenter.core.appointments.dtos;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.appointments.AppointmentState;
import com.isateam.blooddonationcenter.core.centers.Center;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CreateAppointmentDTO {


    private long centerId;
    private LocalDateTime startTime;
    private int duration;



    public Appointment mapToModel() {
        return Appointment.builder()
                .state(AppointmentState.FREE)
                .center(Center.builder().id(centerId).build())
                .startTime(startTime)
                .duration(duration)
                .endTime(startTime.plusMinutes(duration))
                .build();
    }
}
