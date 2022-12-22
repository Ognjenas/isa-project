package com.isateam.blooddonationcenter.core.appointments.dtos;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.users.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentCenterDTO {
    private Long id;

    private String name;

    private Address address;

    private Double averageGrade;

    private String description;


    public AppointmentCenterDTO(Center center) {
        this.id = center.getId();
        this.name = center.getName();
        this.address = center.getAddress();
        this.averageGrade = center.getAverageGrade();
        this.description = center.getDescription();
    }
}
