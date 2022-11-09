package com.isateam.blooddonationcenter.core.centers.dtos;

import com.isateam.blooddonationcenter.core.users.Address;
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
    private Address address;
    private Set<Worker> workers;
}
