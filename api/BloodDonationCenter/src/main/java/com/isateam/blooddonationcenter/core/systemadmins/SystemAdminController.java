package com.isateam.blooddonationcenter.core.systemadmins;

import com.isateam.blooddonationcenter.core.systemadmins.dtos.ChangeSystemAdminPasswordDto;
import com.isateam.blooddonationcenter.core.systemadmins.dtos.CreateSystemAdminDto;
import com.isateam.blooddonationcenter.core.systemadmins.interfaces.ISystemAdminService;
import com.isateam.blooddonationcenter.core.utils.session.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/system-admin")
public class SystemAdminController {

    private final ISystemAdminService systemAdminService;
    private final UserUtils userUtils;

    @PostMapping
    public void register(@Valid @RequestBody CreateSystemAdminDto createSystemAdminDto) {
        systemAdminService.create(createSystemAdminDto.map());
    }

    @PutMapping("/change-password")
    public SystemAdmin updateSystemAdminPassword(@RequestBody String password ){
        long idUser = userUtils.getLoggedId();
        return systemAdminService.changePassword(password, idUser);
    }
}
