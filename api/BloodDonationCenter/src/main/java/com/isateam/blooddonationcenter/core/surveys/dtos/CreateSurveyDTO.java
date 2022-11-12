package com.isateam.blooddonationcenter.core.surveys.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CreateSurveyDTO {
    private List<AnswerDTO> answers;

    public Map<Long, String> getAnswersMap() {
        Map<Long, String> answersMap = new HashMap<>();
        answers.forEach(a -> answersMap.put((long) a.getQuestionId(), a.getAnswer()));
        return answersMap;
    }
}
