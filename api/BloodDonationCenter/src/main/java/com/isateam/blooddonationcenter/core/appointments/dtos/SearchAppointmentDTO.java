package com.isateam.blooddonationcenter.core.appointments.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SearchAppointmentDTO {
    private long centerId;
    private LocalDate date;
}
