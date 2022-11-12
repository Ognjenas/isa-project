package com.isateam.blooddonationcenter.core.surveys.interfaces;

import com.isateam.blooddonationcenter.core.surveys.SurveyQuestion;

import java.util.List;
import java.util.Map;

public interface ISurveyService {
    List<SurveyQuestion> getQuestions();
    void createSurvey(Map<Long, String> answers);
}
