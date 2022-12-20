package com.isateam.blooddonationcenter.core.systemadmins;

import com.isateam.blooddonationcenter.core.systemadmins.dtos.ChangeSystemAdminPasswordDto;
import com.isateam.blooddonationcenter.core.systemadmins.dtos.CreateSystemAdminDto;
import com.isateam.blooddonationcenter.core.systemadmins.interfaces.ISystemAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/system-admin")
public class SystemAdminController {

    private final ISystemAdminService systemAdminService;

    @PostMapping
    public void register(@Valid @RequestBody CreateSystemAdminDto createSystemAdminDto) {
        systemAdminService.create(createSystemAdminDto.map());
    }

    @PutMapping("/change-password")
    public SystemAdmin updateSystemAdminPassword(@RequestBody ChangeSystemAdminPasswordDto passwordChangeDto){
        return systemAdminService.changePassword(passwordChangeDto);
    }
}
