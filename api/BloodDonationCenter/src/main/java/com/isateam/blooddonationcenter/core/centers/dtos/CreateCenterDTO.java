package com.isateam.blooddonationcenter.core.centers.dtos;
import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.users.dtos.CreateAddressDTO;
import com.isateam.blooddonationcenter.core.workers.Worker;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
@Getter
@Setter
public class CreateCenterDTO {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private CreateAddressDTO address;
    @NotNull
    @NotEmpty
    private String description;

    public Center map() {
        return Center.builder()
                .id(null)
                .name(name)
                .address(address.map())
                .averageGrade(0.0)
                .description(description)
                .workers(new HashSet<>())
                .build();
    }
}
