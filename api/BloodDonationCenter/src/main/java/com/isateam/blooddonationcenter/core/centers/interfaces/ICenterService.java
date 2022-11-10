package com.isateam.blooddonationcenter.core.centers.interfaces;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CreateCenterDto;

public interface ICenterService {
    AllCentersDto getAll();
    AllCentersDto sort(String field, String sort);
    Center create(Center center);
    Center update(Center center);
    CenterDto getById(long id);
}
