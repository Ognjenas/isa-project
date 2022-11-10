package com.isateam.blooddonationcenter.core.workers.dtos;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.users.dtos.CreateUserDTO;
import com.isateam.blooddonationcenter.core.workers.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateWorkerDto {
    @NotNull
    long centerId;
    @NotNull
    CreateUserDTO user;

    public Worker map() {
        return Worker.builder()
                .id(null)
                .user(user.map())
                .center(Center.builder().id(centerId).build())
                .build();
    }
}
