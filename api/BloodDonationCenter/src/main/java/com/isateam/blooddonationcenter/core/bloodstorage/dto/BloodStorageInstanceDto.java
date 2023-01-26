package com.isateam.blooddonationcenter.core.bloodstorage.dto;

import com.isateam.blooddonationcenter.core.blooddonations.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BloodStorageInstanceDto {

    private BloodType bloodType;
    private double quantity;
}
