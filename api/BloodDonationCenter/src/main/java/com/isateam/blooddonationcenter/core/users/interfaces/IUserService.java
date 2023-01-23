package com.isateam.blooddonationcenter.core.users.interfaces;

import com.isateam.blooddonationcenter.core.users.dtos.UpdateUserDTO;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.dtos.UserProfileDTO;

import java.util.List;

public interface IUserService {

    User getOne(long id);
    User updateOne(UpdateUserDTO user);
    void create(User user);
    List<User> getAllUsers();
    List<UserProfileDTO> getSearchedUsers(long workerId, String name, String surname);
    User getByEmail(String email);
    void activateUser(String uuid, long id);
    boolean checkFirstLoginAdmin(long loggedId);
}
