package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.users.dtos.UpdateUserDTO;
import com.isateam.blooddonationcenter.core.users.dtos.UserProfileDTO;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;


    @GetMapping("/{id}")
    public UserProfileDTO getOne(@PathVariable("id") long id) throws NotFoundException {
        User user = userService.getOne(id);
        return new UserProfileDTO(user);
    }

    @PatchMapping("/{id}")
    public UserProfileDTO updateOne(
            @Valid @RequestBody UpdateUserDTO user,
            @PathVariable("id") long id
    )  throws NotFoundException {
        user.setId(id);
        User updated = userService.updateOne(user);
        return new UserProfileDTO(updated);
    }

}
