package com.isateam.blooddonationcenter.core.bloodcontracts;


import com.isateam.blooddonationcenter.core.bloodcontracts.dtos.BloodTypeReserveDTO;
import com.isateam.blooddonationcenter.core.bloodcontracts.interfaces.IBloodContractRepository;
import com.isateam.blooddonationcenter.core.bloodcontracts.interfaces.IBloodContractService;
import com.isateam.blooddonationcenter.core.bloodcontracts.interfaces.IBloodTypeReserveDTO;
import com.isateam.blooddonationcenter.core.bloodcontracts.interfaces.IContractEmailDTO;
import com.isateam.blooddonationcenter.core.blooddonations.BloodType;
import com.isateam.blooddonationcenter.core.bloodstorage.BloodStorage;
import com.isateam.blooddonationcenter.core.bloodstorage.interfaces.IBloodStorageDao;
import com.isateam.blooddonationcenter.core.email.EmailDetails;
import com.isateam.blooddonationcenter.core.email.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodContractService implements IBloodContractService {

    private final IEmailService emailService;
    private final IBloodContractRepository bloodContractRepository;
    private final IBloodStorageDao bloodStorageRepository;

    @Override
    public void createContract(BloodContract contract) {
        BloodContract existing = bloodContractRepository.findByEmail(contract.getEmail());
        if(existing != null) {
            existing.setState(ContractState.TERMINATED);
            bloodContractRepository.save(existing);
        }
        contract.setState(ContractState.ACTIVE);
        bloodContractRepository.save(contract);
    }


//    @Scheduled(fixedDelay = 5000)
    public void checkReserves() {
        LocalDate today = LocalDate.now();
        LocalDate first = today.minusDays(today.getDayOfMonth() - 1);
        LocalDate nextMonthFirst = first.plusMonths(1);


        boolean forNotification = today.plusDays(6).equals(nextMonthFirst);
        boolean forDelivery = today.getDayOfMonth() == 1;

        if(forNotification) {
            List<IBloodTypeReserveDTO> amounts = bloodContractRepository.getBloodTypeAmounts();

            for(IBloodTypeReserveDTO dto: amounts) {
                BloodType type = dto.getType();
                BloodStorage storage = bloodStorageRepository.findBloodStorageByBloodTypeAndCenter_Id(dto.getType(), dto.getCenterId());
                if(storage.getQuantity() < dto.getAmount()) {
                    System.out.println("Nema dovoljno krvi nazalost!");
//                sendEmailsForBloodType(dto.getType(), dto.getCenterId(), "Currently not enough blood for shipment!");
                } else {
                    System.out.println("Dovoljno je krvi");
                }
            }
        }

        if(true) {
            List<IBloodTypeReserveDTO> amounts = bloodContractRepository.getBloodTypeAmounts();

            for(IBloodTypeReserveDTO dto: amounts) {
                BloodStorage storage = bloodStorageRepository.findBloodStorageByBloodTypeAndCenter_Id(dto.getType(), dto.getCenterId());
                if(storage.getQuantity() < dto.getAmount()) {
                    sendEmailsForBloodType(dto.getType(), dto.getCenterId(), "Not enough blood for your shipment!");
                    return;
                }

                storage.setQuantity(storage.getQuantity() - dto.getAmount());
                bloodStorageRepository.save(storage);
            }
        }



    }


    private void sendEmailsForBloodType(BloodType type, long centerId, String message) {
        List<IContractEmailDTO> emails = bloodContractRepository.getEmailsForBloodTypeContracts(type.ordinal(), centerId);
        for(IContractEmailDTO hospital: emails) {
            EmailDetails details = EmailDetails.builder()
                    .subject("Blood storage")
                    .body(message)
                    .recipient(hospital.getEmail())
                    .build();

            emailService.sendSimpleMail(details);
        }

    }
}
