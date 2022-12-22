package com.isateam.blooddonationcenter.core.blooddonations;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.appointments.AppointmentState;
import com.isateam.blooddonationcenter.core.appointments.interfaces.IAppointmentDao;
import com.isateam.blooddonationcenter.core.blooddonations.interfaces.IBloodDonationDao;
import com.isateam.blooddonationcenter.core.blooddonations.interfaces.IBloodDonationService;
import com.isateam.blooddonationcenter.core.bloodstorage.BloodStorage;
import com.isateam.blooddonationcenter.core.bloodstorage.interfaces.IBloodStorageDao;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.surveys.Survey;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BloodDonationService implements IBloodDonationService {
    private final IAppointmentDao appointmentDao;
    private final IBloodDonationDao bloodDonationDao;
    private final IUserEntityDao userEntityDao;
    private final IBloodStorageDao bloodStorageDao;

    @Override
    public void create(BloodDonation bloodDonation) {
        if(checkShowUp(bloodDonation)){
            bloodDonation.setFinishTime(LocalDateTime.now());
            BloodDonation donation = bloodDonationDao.save(bloodDonation);
            finishAppointment(bloodDonation.getAppointment());
            cancelSurvey(donation.getUser());
            fillBloodStorage(bloodDonation);
        }
    }



    private boolean checkShowUp(BloodDonation donation) {
        DonationSurvey survey = donation.getDonationSurvey();
        if(survey.isDidntShowUp()){
            userPenalty(donation.getUser());
            finishAppointment(donation.getAppointment());
            return false;
        }
        return true;
    }

    private void cancelSurvey(User userShell) {
        User user = userEntityDao.findById(userShell.getId()).orElseThrow(() -> new BadRequestException("User does not exist"));
        Set<Survey> surveys= user.getSurveys();
        Survey survey = surveys.stream().filter(s -> !s.isUsed()).findFirst().orElseThrow(() -> new BadRequestException("Valid Survey does not exist"));
        surveys.remove(survey);
        survey.setUsed(true);
        surveys.add(survey);
        user.setSurveys(surveys);
        userEntityDao.save(user);
    }

    private void fillBloodStorage(BloodDonation bloodDonation) {
        Appointment appointment = appointmentDao.findById(bloodDonation.getAppointment().getId()).orElseThrow(() -> new BadRequestException("Appointment does not exist"));
//        BloodStorage bloodStorage = bloodStorageDao.findBloodStorageByBloodTypeAndCenter_Id(bloodDonation.getDonationSurvey().getBloodType(),appointment.getCenter().getId());
        BloodStorage bloodStorage = bloodStorageDao.findBloodStorageByBloodTypeAndCenter_Id(bloodDonation.getDonationSurvey().getBloodType(),1);
        bloodStorage.setQuantity(bloodStorage.getQuantity()+bloodDonation.getDonationSurvey().getDonatedAmount());
        bloodStorageDao.save(bloodStorage);
    }


    private void userPenalty(User userShell) {
        User user = userEntityDao.findById(userShell.getId()).orElseThrow(() -> new BadRequestException("User does not exist"));
        user.setPenaltyPoints(user.getPenaltyPoints()+1);
        userEntityDao.save(user);
    }

    private void finishAppointment(Appointment appointmentShell) {
        Appointment appointment = appointmentDao.findById(appointmentShell.getId()).orElseThrow(() -> new BadRequestException("Appointment does not exist"));
        appointment.setState(AppointmentState.FINISHED);
        appointmentDao.save(appointment);
    }
}
