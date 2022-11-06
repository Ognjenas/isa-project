package com.isateam.blooddonationcenter.database.users;


import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.users.utils.Sex;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Sex sex;
    @OneToOne
    private AddressEntity address;
    private String uid;
    private String profession;
    private String school;
}
