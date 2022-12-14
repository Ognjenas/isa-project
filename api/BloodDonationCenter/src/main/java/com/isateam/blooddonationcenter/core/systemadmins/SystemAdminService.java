package com.isateam.blooddonationcenter.core.systemadmins;

import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.systemadmins.dtos.ChangeSystemAdminPasswordDto;
import com.isateam.blooddonationcenter.core.systemadmins.interfaces.ISystemAdminDao;
import com.isateam.blooddonationcenter.core.systemadmins.interfaces.ISystemAdminService;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemAdminService implements ISystemAdminService {

    private final ISystemAdminDao systemAdminDao;
    private final IUserEntityDao userEntityDao;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SystemAdmin create(SystemAdmin systemAdmin) {
        if (userEntityDao.findByEmail(systemAdmin.getUser().getEmail()).isPresent()) {
            throw new BadRequestException("Given email is already in use");
        }
        systemAdminDao.save(systemAdmin);
        return systemAdmin;
    }

    @Override
    public SystemAdmin getById(long id) {
        SystemAdmin systemAdmin=systemAdminDao.findById(id).orElseThrow(()-> new NotFoundException("System administrator does not exist"));
        return systemAdmin;
    }

    @Override
    public SystemAdmin changePassword(String password, long idUser) {
        SystemAdmin admin = systemAdminDao.getSystemAdminByUser_Id(idUser);
        User user = admin.getUser();
        user.setPassword(passwordEncoder.encode(password));
        admin.setFirstLogin(false);
        admin.setUser(user);
        systemAdminDao.save(admin);
        return admin;
    }
}
