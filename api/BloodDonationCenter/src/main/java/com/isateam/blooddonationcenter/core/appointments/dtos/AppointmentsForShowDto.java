package com.isateam.blooddonationcenter.core.appointments.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentsForShowDto {

    @NotBlank
    private String title;
    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;
    private boolean allDay;
}
