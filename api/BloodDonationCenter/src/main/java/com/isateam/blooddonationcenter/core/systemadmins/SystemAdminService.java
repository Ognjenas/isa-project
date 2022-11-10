package com.isateam.blooddonationcenter.core.systemadmins;

import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.systemadmins.interfaces.ISystemAdminDao;
import com.isateam.blooddonationcenter.core.systemadmins.interfaces.ISystemAdminService;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.UserRole;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.desktop.SystemEventListener;

@Service
@RequiredArgsConstructor
public class SystemAdminService implements ISystemAdminService {

    private final IUserEntityDao userEntityDao;
    private final ISystemAdminDao systemAdminDao;

    @Override
    public SystemAdmin getOne(long id) {
        return null;
    }

    @Override
    public void create(User user) {
        if (userEntityDao.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Given email is already in use");
        }
        user.setRole(UserRole.ADMINISTRATOR);
        SystemAdmin systemAdmin= new SystemAdmin(user);
        systemAdminDao.save(systemAdmin);
    }
}
