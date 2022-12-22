package com.isateam.blooddonationcenter.core.bloodstorage.interfaces;

import com.isateam.blooddonationcenter.core.blooddonations.BloodType;
import com.isateam.blooddonationcenter.core.bloodstorage.BloodStorage;
import com.isateam.blooddonationcenter.core.centers.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBloodStorageDao  extends JpaRepository<BloodStorage, Long> {
    BloodStorage findBloodStorageByBloodTypeAndCenter_Id(BloodType bloodType, Long center_id);
}
