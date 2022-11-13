package com.isateam.blooddonationcenter.core.worktime.interfaces;

import com.isateam.blooddonationcenter.core.worktime.WorkTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkTimeDao extends JpaRepository<WorkTime,Long> {
}
