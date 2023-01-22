package com.isateam.blooddonationcenter.core.blooddonations;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "donation_surveys")
public class DonationSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BloodType bloodType;
    private double hemoglobin;
    private double copperSulfate;
    private double heartRateUpper;
    private double heartRateLower;
    private boolean donationIsApproved;
    private boolean didntShowUp;
    private double donatedAmount;
}
