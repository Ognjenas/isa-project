package com.isateam.blooddonationcenter.core.centers.interfaces;

import com.isateam.blooddonationcenter.core.centers.Center;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CenterDao extends JpaRepository<Center, Long> {
}
