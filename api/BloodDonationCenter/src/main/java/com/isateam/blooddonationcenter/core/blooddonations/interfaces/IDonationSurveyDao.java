package com.isateam.blooddonationcenter.core.blooddonations.interfaces;

import com.isateam.blooddonationcenter.core.blooddonations.DonationSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDonationSurveyDao extends JpaRepository<DonationSurvey, Long> {
}
