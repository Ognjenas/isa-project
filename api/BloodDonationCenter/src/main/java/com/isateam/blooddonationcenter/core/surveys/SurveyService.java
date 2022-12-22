package com.isateam.blooddonationcenter.core.surveys;

import com.isateam.blooddonationcenter.core.complaints.Answer;
import com.isateam.blooddonationcenter.core.surveys.interfaces.ISurveyAnswerDao;
import com.isateam.blooddonationcenter.core.surveys.interfaces.ISurveyDao;
import com.isateam.blooddonationcenter.core.surveys.interfaces.ISurveyQuestionDao;
import com.isateam.blooddonationcenter.core.surveys.interfaces.ISurveyService;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.utils.session.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService implements ISurveyService {

    private final ISurveyQuestionDao surveyQuestionDao;

    private final ISurveyAnswerDao surveyAnswerDao;

    private final UserUtils userUtils;

    private final ISurveyDao surveyDao;

    @Override
    public List<SurveyQuestion> getQuestions() {
        return surveyQuestionDao.findAll();
    }

    @Override
    public void createSurvey(Map<Long, String> answers) {
        List<SurveyAnswer> possibleAnswers = surveyAnswerDao.findAll();
        Set<SurveyAnswer> answersToSave = new HashSet<>();
        for(var key : answers.keySet()) {
            Optional<SurveyAnswer> questionAnswer = possibleAnswers.stream()
                    .filter(a -> a.getQuestion().getId().equals(key) && a.getAnswer().equals(answers.get(key)))
                    .findFirst();
            questionAnswer.ifPresent(answersToSave::add);
        }
        Survey survey = Survey.builder()
                .answers(answersToSave)
                .user(User.builder()
                        .id(userUtils.getLoggedId())
                        .build())
                .writingTime(LocalDateTime.now())
                .isUsed(false)
                .build();

        surveyDao.save(survey);
    }
}
