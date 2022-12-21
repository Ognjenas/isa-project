package com.isateam.blooddonationcenter.core.appointments.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreeAppointmentDTO {
    private long id;
    private String centerName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
