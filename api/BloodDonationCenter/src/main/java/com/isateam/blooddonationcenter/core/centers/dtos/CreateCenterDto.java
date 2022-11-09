package com.isateam.blooddonationcenter.core.centers.dtos;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.users.dtos.CreateAddressDTO;
import com.isateam.blooddonationcenter.core.workers.Worker;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateCenterDto {
    private String name;
    private String description;
    private CreateAddressDTO address;

    public Center mapToModel(){
        Address address = this.address.map();
        return Center.builder()
                .address(address)
                .name(name)
                .description(description)
                .averageGrade(0.)
                .build();
    }
}


