package com.isateam.blooddonationcenter.core.blooddonations.dto;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.appointments.AppointmentState;
import com.isateam.blooddonationcenter.core.blooddonations.BloodDonation;
import com.isateam.blooddonationcenter.core.blooddonations.BloodType;
import com.isateam.blooddonationcenter.core.blooddonations.DonationSurvey;
import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.users.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CreateBloodDonationDTO {
    private long appointmentId;
    private long userId;
    private BloodType bloodType;
    private double hemoglobin;
    private double copperSulfate;
    private double heartRateUpper;
    private double heartRateLower;
    private boolean donationIsApproved;
    private boolean didntShowUp;
    private double donatedAmount;

    public BloodDonation mapToModel() {
        DonationSurvey survey = DonationSurvey.builder()
                .bloodType(bloodType)
                .hemoglobin(hemoglobin)
                .copperSulfate(copperSulfate)
                .heartRateUpper(heartRateUpper)
                .heartRateLower(heartRateLower)
                .donationIsApproved(donationIsApproved)
                .didntShowUp(didntShowUp)
                .donatedAmount(donatedAmount)
                .build();
        return BloodDonation.builder()
                .appointment(Appointment.builder().id(appointmentId).build())
                .user(User.builder().id(userId).build())
                .donationSurvey(survey)
                .build();
    }
}
