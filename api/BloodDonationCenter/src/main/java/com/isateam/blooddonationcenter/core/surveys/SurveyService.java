package com.isateam.blooddonationcenter.core.surveys;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentDao;
import com.isateam.blooddonationcenter.core.complaints.Answer;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.surveys.dtos.ResponseAnswerDTO;
import com.isateam.blooddonationcenter.core.surveys.dtos.UserSurveyDTO;
import com.isateam.blooddonationcenter.core.surveys.interfaces.ISurveyAnswerDao;
import com.isateam.blooddonationcenter.core.surveys.interfaces.ISurveyDao;
import com.isateam.blooddonationcenter.core.surveys.interfaces.ISurveyQuestionDao;
import com.isateam.blooddonationcenter.core.surveys.interfaces.ISurveyService;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
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

    private final IAppointmentDao appointmentDao;

    private final IUserEntityDao userEntityDao;

    @Override
    public List<SurveyQuestion> getQuestions() {
        return surveyQuestionDao.findAll();
    }

    public UserSurveyDTO getAnswers(long appointmentId) {
        Appointment appointment = appointmentDao.findById(appointmentId).orElseThrow(() -> new BadRequestException("Appointment does not exist"));
        long userId = appointment.getUser().getId();
        Survey survey = getSurveyByUserId(userId);
        List<ResponseAnswerDTO> QApairs = getAnswersForSurvey(survey);
        return new UserSurveyDTO(userId, QApairs);
    }

    private List<ResponseAnswerDTO> getAnswersForSurvey(Survey survey) {
        List<ResponseAnswerDTO> response = new ArrayList<>();
        Set<SurveyAnswer> answers = survey.getAnswers();
        answers.stream().forEach(answer -> {
            response.add(new ResponseAnswerDTO(answer.getQuestion().getQuestion(), answer.getAnswer()));
        });
        return response;
    }

    private Survey getSurveyByUserId(long userId) {
        User user = userEntityDao.findById(userId).orElseThrow(() -> new BadRequestException("User does not exist"));
        Set<Survey> surveys = user.getSurveys();
        Survey survey = surveys.stream().filter(s -> !s.isUsed()).findFirst().orElseThrow(() -> new BadRequestException("Valid Survey does not exist"));
        return survey;
    }

    @Override
    public void createSurvey(Map<Long, String> answers) {
        List<SurveyAnswer> possibleAnswers = surveyAnswerDao.findAll();
        Set<SurveyAnswer> answersToSave = new HashSet<>();
        for (var key : answers.keySet()) {
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
