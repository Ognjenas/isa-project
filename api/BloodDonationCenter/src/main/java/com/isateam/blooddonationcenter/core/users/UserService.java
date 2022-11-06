package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.users.dtos.UpdateUserDTO;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserRepository;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;

    @Override
    public User getOne(long id) {
        return userRepository.getById(id);
    }

    @Override
    public User updateOne(UpdateUserDTO updated) {
        User user = userRepository.getById(updated.getId());
        User forUpdate = fillUpdated(user, updated);
        userRepository.update(forUpdate);
        return forUpdate;
    }

    private User fillUpdated(User oldUser, UpdateUserDTO newUser) {
        return User.builder()
                .id(oldUser.getId())
                .email(oldUser.getEmail())
                .password(oldUser.getPassword())
                .uid(newUser.getUid())
                .name(newUser.getName())
                .surname(newUser.getSurname())
                .sex(newUser.getSex())
                .address(newUser.getAddress())
                .profession(newUser.getProfession())
                .school(newUser.getSchool())
                .build();
    }

}
