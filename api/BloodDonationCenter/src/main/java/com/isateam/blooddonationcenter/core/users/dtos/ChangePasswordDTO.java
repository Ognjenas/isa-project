package com.isateam.blooddonationcenter.core.users.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordDTO {
    private String oldPassword;
    private String newPassword;
}
