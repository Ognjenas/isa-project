package com.isateam.blooddonationcenter.core.blooddonations.interfaces;

import com.isateam.blooddonationcenter.core.blooddonations.BloodDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBloodDonationDao extends JpaRepository<BloodDonation, Long> {
}
