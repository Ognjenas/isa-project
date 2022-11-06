package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.users.utils.Sex;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Sex sex;
    private Address address;
    private String uid;
    private String profession;
    private String school;
}
