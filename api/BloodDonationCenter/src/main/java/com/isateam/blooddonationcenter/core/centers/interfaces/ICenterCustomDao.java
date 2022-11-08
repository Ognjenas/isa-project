package com.isateam.blooddonationcenter.core.centers.interfaces;

import com.isateam.blooddonationcenter.core.centers.Center;

import java.util.List;

public interface ICenterCustomDao {
    List<Center> getSorted(String field, String sort);
}
