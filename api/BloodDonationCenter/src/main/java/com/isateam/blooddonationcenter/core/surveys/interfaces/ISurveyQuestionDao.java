package com.isateam.blooddonationcenter.core.surveys.interfaces;

import com.isateam.blooddonationcenter.core.surveys.SurveyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISurveyQuestionDao extends JpaRepository<SurveyQuestion, Long> {
}
