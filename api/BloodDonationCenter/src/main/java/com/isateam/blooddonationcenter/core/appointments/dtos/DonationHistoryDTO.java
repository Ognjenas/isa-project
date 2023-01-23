package com.isateam.blooddonationcenter.core.appointments.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.isateam.blooddonationcenter.core.appointments.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationHistoryDTO {

    private long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    private int duration;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;
    private String user;
    private long center;

    public DonationHistoryDTO(Appointment appointment) {
        id = appointment.getId();
        startTime = appointment.getStartTime();
        duration = appointment.getDuration();
        endTime = appointment.getEndTime();
        user = appointment.getUser().getName() + appointment.getUser().getSurname();
        center = appointment.getCenter().getId();
    }
}
