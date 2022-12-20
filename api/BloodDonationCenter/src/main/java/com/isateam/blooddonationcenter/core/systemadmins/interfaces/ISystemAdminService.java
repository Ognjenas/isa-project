package com.isateam.blooddonationcenter.core.systemadmins.interfaces;

import com.isateam.blooddonationcenter.core.systemadmins.SystemAdmin;
import com.isateam.blooddonationcenter.core.systemadmins.dtos.ChangeSystemAdminPasswordDto;

public interface ISystemAdminService {

    SystemAdmin create(SystemAdmin systemAdmin);

    SystemAdmin getById(long id);

    SystemAdmin changePassword(ChangeSystemAdminPasswordDto updateWorkerDto);
}
