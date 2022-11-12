package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.users.dtos.UpdateUserDTO;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserEntityDao userEntityDao;

    @Override
    public User getOne(long id) {
        return userEntityDao.findById(id).orElseThrow(() -> new BadRequestException("User with given id does not exist"));
    }

    @Override
    public User updateOne(UpdateUserDTO updated) {
        User user = userEntityDao.findById(updated.getId()).orElseThrow(() -> new BadRequestException("User with given id does not exist"));
        User forUpdate = fillUpdated(user, updated);
        userEntityDao.save(forUpdate);
        return forUpdate;
    }

    @Override
    public void create(User user) {
        if (userEntityDao.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Given email is already in use");
        }
        userEntityDao.save(user);
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

    public List<User> getAllUsers(){
        return userEntityDao.findAll();
    }
    public List<User> getSearchedUsers(String name, String surname){
        if( name.trim().equals("") && surname.trim().equals(""))
            return userEntityDao.findAll();
        return userEntityDao.findAllByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(name, surname);
    }
}
