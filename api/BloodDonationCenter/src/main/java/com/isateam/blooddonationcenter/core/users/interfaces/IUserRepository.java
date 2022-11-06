package com.isateam.blooddonationcenter.core.users.interfaces;

import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.users.User;

public interface IUserRepository {
    User getById(long id) throws NotFoundException;
    void update(User user);

}
