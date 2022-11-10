package com.isateam.blooddonationcenter.core.systemadmins;
import com.isateam.blooddonationcenter.core.systemadmins.interfaces.ISystemAdminService;
import com.isateam.blooddonationcenter.core.users.dtos.CreateUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/systemadmins")
public class SystemAdminController {

    private final ISystemAdminService systemAdminService;

    @PostMapping
    public void register(@Valid @RequestBody CreateUserDTO createUserDTO) {systemAdminService.create(createUserDTO.map());}
}