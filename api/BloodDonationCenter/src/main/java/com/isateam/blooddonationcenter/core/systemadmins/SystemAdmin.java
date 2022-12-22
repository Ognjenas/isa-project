package com.isateam.blooddonationcenter.core.systemadmins;

import com.isateam.blooddonationcenter.core.users.User;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "system_admins")

public class SystemAdmin {

    @Id
    @SequenceGenerator(name = "system_admin_seq",
            sequenceName = "system_admin_sequence",
            initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_admin_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private User user;

    private boolean firstLogin;

}
