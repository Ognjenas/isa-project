package com.isateam.blooddonationcenter.core.surveys.interfaces;

import com.isateam.blooddonationcenter.core.surveys.SurveyQuestion;
import com.isateam.blooddonationcenter.core.surveys.dtos.UserSurveyDTO;

import java.util.List;
import java.util.Map;

public interface ISurveyService {
    List<SurveyQuestion> getQuestions();
    UserSurveyDTO getAnswers(long id);
    void createSurvey(Map<Long, String> answers);
}
