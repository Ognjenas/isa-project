package com.isateam.blooddonationcenter.core.appointments.dtos;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class FreeAppointmentsDTO {
    private List<FreeAppointmentDTO> appointments = new ArrayList<>();

    public FreeAppointmentsDTO(List<Appointment> appointments) {
        this.appointments.addAll(appointments.stream().map(a -> FreeAppointmentDTO.builder()
                .id(a.getId())
                .centerName(a.getCenter().getName())
                .startTime(a.getStartTime())
                .endTime(a.getStartTime().plusMinutes(a.getDuration()))
                .build()).collect(Collectors.toList()));
    }
}
