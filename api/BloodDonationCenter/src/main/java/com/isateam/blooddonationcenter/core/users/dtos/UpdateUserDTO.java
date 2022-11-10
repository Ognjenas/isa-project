package com.isateam.blooddonationcenter.core.users.dtos;

import com.isateam.blooddonationcenter.core.users.utils.Sex;
import com.isateam.blooddonationcenter.core.users.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class UpdateUserDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private Sex sex;
    @NotNull
    private Address address;
    @NotNull
    private String uid;
    @NotNull
    private String profession;
    @NotNull
    private String school;
}
