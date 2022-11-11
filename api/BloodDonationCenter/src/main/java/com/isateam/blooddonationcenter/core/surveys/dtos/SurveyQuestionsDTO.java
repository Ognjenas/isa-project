package com.isateam.blooddonationcenter.core.surveys.dtos;

import com.isateam.blooddonationcenter.core.surveys.SurveyQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SurveyQuestionsDTO {
    private List<SurveyQuestionDTO> questions = new ArrayList<>();

    public SurveyQuestionsDTO(List<SurveyQuestion> surveyQuestions) {
        surveyQuestions.forEach(q -> questions.add(new SurveyQuestionDTO(q.getId(), q.getQuestion())));
    }
}
