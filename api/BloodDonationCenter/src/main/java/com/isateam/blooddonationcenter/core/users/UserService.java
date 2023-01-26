package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.email.EmailDetails;
import com.isateam.blooddonationcenter.core.email.IEmailService;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.systemadmins.SystemAdmin;
import com.isateam.blooddonationcenter.core.systemadmins.interfaces.ISystemAdminDao;
import com.isateam.blooddonationcenter.core.users.dtos.UpdateUserDTO;
import com.isateam.blooddonationcenter.core.users.dtos.UserProfileDTO;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserValidationDao;
import com.isateam.blooddonationcenter.core.workers.Worker;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserEntityDao userEntityDao;

    private final IUserValidationDao userValidationDao;

    private final IEmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private final ISystemAdminDao adminDao;

    private final IWorkerDao workerDao;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntityDao.save(user);
        prepareForValidation(user);
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
                .role(oldUser.getRole())
                .activated(oldUser.isActivated())
                .build();
    }

    public List<User> getAllUsers() {
        return userEntityDao.findAll();
    }

    public List<UserProfileDTO> getSearchedUsers(long workerId, String name, String surname) {
        Worker worker = workerDao.findByUser_Id(workerId);
        if (worker == null)
            throw new BadRequestException("Worker does not exist");

        long centerId = worker.getCenter().getId();
        if (name.trim().equals("") && surname.trim().equals(""))
            return mapUsersToProfileDtos(userEntityDao.findUsersThatDonatedInCenter(centerId));

        String nameSearch = "%".concat(name).concat("%");
        String surnameSearch = "%".concat(surname).concat("%");
        List<User> users = userEntityDao.findUsersThatDonatedInCenterBySearch(centerId, nameSearch, surnameSearch);
        return mapUsersToProfileDtos(users);
    }

    @Override
    public User getByEmail(String email) {
        return userEntityDao.findByEmail(email).orElseThrow();
    }

    @Override
    public void activateUser(String uuid, long id) {
        UserValidation userValidation = userValidationDao.findByUuid(uuid).orElseThrow();
        User user = userEntityDao.findById(id).orElseThrow();

        if (userValidation.getUser().getId() == id) {
            user.setActivated(true);
            userEntityDao.save(user);
            userValidationDao.delete(userValidation);
        }
    }

    @Override
    public boolean checkFirstLoginAdmin(long loggedId) {
        User user = userEntityDao.getById(loggedId);
        if (user.getRole().equals(UserRole.ADMINISTRATOR)) {
            SystemAdmin admin = adminDao.getSystemAdminByUser_Id(loggedId);
            boolean firstLog = admin.isFirstLogin();
            return firstLog;
        } else return false;
    }

    private List<UserProfileDTO> mapUsersToProfileDtos(List<User> users) {
        return users.stream().map(u -> new UserProfileDTO(u)).toList();
    }

    private void prepareForValidation(User user) {
        String uuid = UUID.randomUUID().toString();
        userValidationDao.save(
                UserValidation.builder()
                        .user(user)
                        .uuid(uuid)
                        .build()
        );
        emailService.sendSimpleMail(getEmailDetailsForCreating(user.getEmail(), uuid, user.getId()));
    }

    private EmailDetails getEmailDetailsForCreating(String email, String uuid, long id) {
        return EmailDetails.builder()
                .body("http://localhost:8000/validate/" + uuid + "/" + id)
                .recipient(email)
                .subject("Activation of account")
                .build();
    }

}
