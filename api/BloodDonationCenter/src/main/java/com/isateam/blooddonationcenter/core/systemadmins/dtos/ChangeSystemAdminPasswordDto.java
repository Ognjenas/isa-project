package com.isateam.blooddonationcenter.core.systemadmins.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
public class ChangeSystemAdminPasswordDto {

    @NotBlank
    private String password;

}
