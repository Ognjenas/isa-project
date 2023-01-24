package com.isateam.blooddonationcenter.core.surveys.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserSurveyDTO {
    public long userId;
    public List<ResponseAnswerDTO> answers;

}
