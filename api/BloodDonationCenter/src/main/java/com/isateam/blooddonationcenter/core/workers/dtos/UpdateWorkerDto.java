package com.isateam.blooddonationcenter.core.workers.dtos;

import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.utils.Sex;
import com.isateam.blooddonationcenter.core.workers.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UpdateWorkerDto {
    @NotNull
    @NotEmpty
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surname;

    @NotNull
    @NotEmpty
    private Sex sex;

    @NotNull
    @NotEmpty
    private String uid;

    @NotNull
    @NotEmpty
    private String profession;

    @NotNull
    @NotEmpty
    private String school;

    @NotNull
    @NotEmpty
    private Address address;

    public Worker mapToModel(User user){
        user.setName(name);
        user.setSurname(surname);
        user.setSex(sex);
        user.setUid(uid);
        user.setProfession(profession);
        user.setSchool(school);
        user.setAddress(address);
        return Worker.builder()
                .id(id)
                .user(user)
                .build();
    }
}
