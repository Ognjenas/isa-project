package com.isateam.blooddonationcenter.core.appointments.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentsForShowDto {

    @NotBlank
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull
    private LocalDateTime start;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull
    private LocalDateTime end;
    private Boolean allDay;

}
