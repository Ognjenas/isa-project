package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.users.utils.Sex;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Sex sex;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;
    private String uid;
    private String profession;
    private String school;
    private UserRole role;
}
