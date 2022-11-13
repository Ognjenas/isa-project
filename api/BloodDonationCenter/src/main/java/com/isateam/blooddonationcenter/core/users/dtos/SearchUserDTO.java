package com.isateam.blooddonationcenter.core.users.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class SearchUserDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
}
