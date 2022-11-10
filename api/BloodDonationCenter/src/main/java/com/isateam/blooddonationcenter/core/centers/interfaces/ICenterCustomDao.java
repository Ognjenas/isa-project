package com.isateam.blooddonationcenter.core.centers.interfaces;

import com.isateam.blooddonationcenter.core.centers.Center;

import java.util.List;
import java.util.Map;

public interface ICenterCustomDao {
    List<Center> getSorted(String field, String sort);
    List<Center> getFiltered(Map<String, String> queryParams);
}
