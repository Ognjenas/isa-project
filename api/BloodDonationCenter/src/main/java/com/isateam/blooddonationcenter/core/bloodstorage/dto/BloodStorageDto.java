package com.isateam.blooddonationcenter.core.bloodstorage.dto;

import com.isateam.blooddonationcenter.core.bloodstorage.BloodStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BloodStorageDto {
    private List<BloodStorageInstanceDto> bloodStorage;
}
