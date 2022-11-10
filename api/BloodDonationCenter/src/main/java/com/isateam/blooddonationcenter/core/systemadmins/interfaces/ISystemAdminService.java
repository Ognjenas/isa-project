package com.isateam.blooddonationcenter.core.systemadmins.interfaces;

import com.isateam.blooddonationcenter.core.systemadmins.SystemAdmin;
import com.isateam.blooddonationcenter.core.users.User;

public interface ISystemAdminService {

    SystemAdmin getOne(long id);
    void create(User user);
}