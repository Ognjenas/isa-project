package com.isateam.blooddonationcenter.core.centers.dtos;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.users.Address;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateCenterDto {
    private long id;
    private String name;
    private String description;
    private Address address;

    public Center mapToModel() {
        return Center.builder()
                .id(id)
                .address(address)
                .name(name)
                .description(description)
                .build();
    }
}
