package com.isateam.blooddonationcenter.core.surveys.interfaces;

import com.isateam.blooddonationcenter.core.surveys.SurveyAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISurveyAnswerDao extends JpaRepository<SurveyAnswer, Long> {
}
