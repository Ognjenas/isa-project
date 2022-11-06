package com.isateam.blooddonationcenter.core.users.dtos;

import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.utils.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserDTO {
    @NotNull
    @NotEmpty
    private String email;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String surname;
    @NotNull
    private Sex sex;
    @NotNull
    private CreateAddressDTO address;
    @NotNull
    @NotEmpty
    private String uid;
    @NotNull
    @NotEmpty
    private String profession;
    @NotNull
    @NotEmpty
    private String school;

    public User map() {
        return User.builder()
                .id(null)
                .address(address.map())
                .name(name)
                .email(email)
                .password(password)
                .profession(profession)
                .sex(sex)
                .school(school)
                .uid(uid)
                .surname(surname)
                .build();
    }
}
