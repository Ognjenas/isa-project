package com.isateam.blooddonationcenter.core.users.interfaces;

import com.isateam.blooddonationcenter.core.users.dtos.UpdateUserDTO;
import com.isateam.blooddonationcenter.core.users.User;

import java.util.List;

public interface IUserService {

    User getOne(long id);
    User updateOne(UpdateUserDTO user);
    void create(User user);
    List<User> getAllUsers();
    List<User> getSearchedUsers(String name, String surname);
}
