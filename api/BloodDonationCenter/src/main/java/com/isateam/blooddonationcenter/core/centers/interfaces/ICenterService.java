package com.isateam.blooddonationcenter.core.centers.interfaces;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CreateCenterDto;
import com.isateam.blooddonationcenter.core.worktime.WorkTime;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICenterService {
    AllCentersDto getAll(Map<String, String> map);
//    AllCentersDto sort(String field, String sort);
    Center create(Center center);
    Center update(Center center);
    CenterDto getById(long id,long user_id);
    void addWorkTimeToCenter(Set<WorkTime> mapWorkTimeToModel);

}
