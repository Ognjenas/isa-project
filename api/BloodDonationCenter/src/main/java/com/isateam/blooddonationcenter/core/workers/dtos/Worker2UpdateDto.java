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
public class Worker2UpdateDto {
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

    @NotNull
    @NotEmpty
    private String hospitalName;


    public Worker2UpdateDto(Worker worker) {
        User user=worker.getUser();
        this.id= worker.getId();
        this.name=user.getName();
        this.surname=user.getSurname();
        this.sex=user.getSex();
        this.uid=user.getUid();
        this.profession=user.getProfession();
        this.school=user.getSchool();
        this.address=user.getAddress();
        this.hospitalName=worker.getCenter().getName();
    }

}
