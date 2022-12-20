package com.isateam.blooddonationcenter.core.systemadmins.dtos;
import com.isateam.blooddonationcenter.core.systemadmins.SystemAdmin;
import com.isateam.blooddonationcenter.core.users.dtos.CreateUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor

public class CreateSystemAdminDto {
    CreateUserDTO user;
    @NotNull
    long systemAdminId;

    public SystemAdmin map() {
        return SystemAdmin.builder()
                .id(null)
                .user(user.map())
                .firstLogin(true)
                .build();
    }
}
