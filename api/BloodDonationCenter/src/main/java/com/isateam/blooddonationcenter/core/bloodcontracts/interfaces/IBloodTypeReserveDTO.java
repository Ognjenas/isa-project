package com.isateam.blooddonationcenter.core.bloodcontracts.interfaces;

import com.isateam.blooddonationcenter.core.blooddonations.BloodType;

public interface IBloodTypeReserveDTO {

    BloodType getType();
    double getAmount();
    long getCenterId();
}
