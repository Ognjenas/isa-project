package com.isateam.blooddonationcenter.core.surveys.interfaces;

import com.isateam.blooddonationcenter.core.surveys.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISurveyDao extends JpaRepository<Survey, Long> {
}
