package com.isateam.blooddonationcenter.core.donations;


import com.isateam.blooddonationcenter.core.users.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "centers")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private double quantity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date donatedAt;

    private BloodType bloodType;

}
