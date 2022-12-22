package com.isateam.blooddonationcenter.core.systemadmins.interfaces;

import com.isateam.blooddonationcenter.core.systemadmins.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISystemAdminDao extends JpaRepository<SystemAdmin, Long>{

}
