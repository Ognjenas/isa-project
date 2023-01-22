package com.isateam.blooddonationcenter.core.blooddonations;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.users.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "blood_donations")
public class BloodDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Appointment appointment;
    @ManyToOne
    private User user;
    private LocalDateTime finishTime;
    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    private DonationSurvey donationSurvey;
}
