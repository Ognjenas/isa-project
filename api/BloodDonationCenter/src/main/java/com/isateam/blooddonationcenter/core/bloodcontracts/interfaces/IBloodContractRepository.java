package com.isateam.blooddonationcenter.core.bloodcontracts.interfaces;

import com.isateam.blooddonationcenter.core.bloodcontracts.BloodContract;
import com.isateam.blooddonationcenter.core.bloodcontracts.dtos.BloodTypeReserveDTO;
import com.isateam.blooddonationcenter.core.blooddonations.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBloodContractRepository extends JpaRepository<BloodContract, Long> {
    BloodContract findByEmail(String email);


    @Query(value = "select center_id as centerId, type, sum(quantity) as amount from blood_contracts where state=1 group by center_id, type", nativeQuery = true)
    List<IBloodTypeReserveDTO> getBloodTypeAmounts();


    @Query(value = "select email from blood_contracts where state=1 and type=?1 and center_id=?2", nativeQuery = true)
    List<IContractEmailDTO> getEmailsForBloodTypeContracts(int type, long centerId);
}
