package com.isateam.blooddonationcenter.core.systemadmins.dtos;
import com.isateam.blooddonationcenter.core.systemadmins.SystemAdmin;
import com.isateam.blooddonationcenter.core.users.dtos.CreateUserAdminDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor

public class CreateSystemAdminDto {
    CreateUserAdminDTO user;

    public SystemAdmin map() {
        return SystemAdmin.builder()
                .id(null)
                .user(user.map())
                .firstLogin(true)
                .build();
    }
}
