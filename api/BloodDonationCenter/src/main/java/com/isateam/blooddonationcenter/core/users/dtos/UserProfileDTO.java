package com.isateam.blooddonationcenter.core.users.dtos;

import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.utils.Sex;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDTO {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private Sex sex;
    private Address address;
    private String uid;
    private String profession;
    private String school;

    public UserProfileDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.surname = user.getSurname();
        this.name = user.getName();
        this.sex = user.getSex();
        this.address = user.getAddress();
        this.uid = user.getUid();
        this.profession = user.getProfession();
        this.school = user.getSchool();
    }

}
