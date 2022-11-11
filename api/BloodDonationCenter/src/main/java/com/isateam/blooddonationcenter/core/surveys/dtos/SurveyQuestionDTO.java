package com.isateam.blooddonationcenter.core.surveys.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SurveyQuestionDTO {
    private long id;
    private String question;
}
