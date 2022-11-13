package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.surveys.Survey;
import com.isateam.blooddonationcenter.core.users.utils.Sex;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "user_seq",
            sequenceName = "user_sequence",
            initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
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

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Survey> surveys;
}
