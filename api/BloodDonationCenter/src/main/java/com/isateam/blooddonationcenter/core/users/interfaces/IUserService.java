package com.isateam.blooddonationcenter.core.users.interfaces;

import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.dtos.UpdateUserDTO;

public interface IUserService {

    User getOne(long id);
    User updateOne(UpdateUserDTO user);
}
