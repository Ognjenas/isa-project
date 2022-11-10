package com.isateam.blooddonationcenter.core.systemadmins;

import com.isateam.blooddonationcenter.core.users.User;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "systemadmins")
public class SystemAdmin {

    @Autowired
    public SystemAdmin(User user) {
        this.user = user;
        this.passwordChanged = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    private boolean passwordChanged;

}