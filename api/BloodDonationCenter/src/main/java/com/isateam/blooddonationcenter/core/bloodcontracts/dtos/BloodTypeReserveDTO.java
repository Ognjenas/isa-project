package com.isateam.blooddonationcenter.core.bloodcontracts.dtos;

import com.isateam.blooddonationcenter.core.blooddonations.BloodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BloodTypeReserveDTO {
    private BloodType type;
    private double amount;
}
