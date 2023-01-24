package com.isateam.blooddonationcenter.core.surveys;

import com.isateam.blooddonationcenter.core.surveys.dtos.CreateSurveyDTO;
import com.isateam.blooddonationcenter.core.surveys.dtos.SurveyQuestionsDTO;
import com.isateam.blooddonationcenter.core.surveys.dtos.UserSurveyDTO;
import com.isateam.blooddonationcenter.core.surveys.interfaces.ISurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RequiredArgsConstructor
@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final ISurveyService surveyService;

    @PreAuthorize("hasRole('REGULAR')")
    @GetMapping
    public SurveyQuestionsDTO getAllQuestions() {
        return new SurveyQuestionsDTO(surveyService.getQuestions());
    }


    @PreAuthorize("hasRole('WORKER')")
    @GetMapping(path = "/{id}")
    public UserSurveyDTO getSurveyForUserByAppointmentId(@PathVariable long id){
        return surveyService.getAnswers(id);
    }

    @PostMapping
    public void createSurvey(@RequestBody CreateSurveyDTO createSurveyDTO) {
        surveyService.createSurvey(createSurveyDTO.getAnswersMap());
    }
}
