package com.isateam.blooddonationcenter.core.appointments.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.users.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class ShowAppointmentDTO {

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    private int duration;
    private AppointmentCenterDTO center;

    public ShowAppointmentDTO(Appointment appointment) {
        this.startTime = appointment.getStartTime();
        this.duration = appointment.getDuration();
        this.id = appointment.getId();
        this.center = new AppointmentCenterDTO(appointment.getCenter());
    }
}
