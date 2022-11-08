package com.isateam.blooddonationcenter.core.centers.interfaces;

import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;

public interface ICenterService {
    AllCentersDto getAll();
    AllCentersDto sort(String field, String sort);
}
