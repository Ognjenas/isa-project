package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/validate")
public class ValidateUserController {

    private final IUserService userService;

    @GetMapping("/{uuid}/{id}")
    public void validateAccount(@PathVariable String uuid,@PathVariable long id) {
        userService.activateUser(uuid, id);
    }
}
