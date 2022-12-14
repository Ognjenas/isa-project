package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.users.dtos.CreateUserDTO;
import com.isateam.blooddonationcenter.core.users.dtos.SearchUserDTO;
import com.isateam.blooddonationcenter.core.users.dtos.UpdateUserDTO;
import com.isateam.blooddonationcenter.core.users.dtos.UserProfileDTO;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import com.isateam.blooddonationcenter.core.utils.session.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;
    private final UserUtils userUtils;

    @GetMapping("/{id}")
    public UserProfileDTO getOne(@PathVariable("id") long id) {
        User user = userService.getOne(id);
        return new UserProfileDTO(user);
    }

    @PatchMapping("/{id}")
    public UserProfileDTO updateOne(
            @Valid @RequestBody UpdateUserDTO user,
            @PathVariable("id") long id
    ) {
        user.setId(id);
        User updated = userService.updateOne(user);
        return new UserProfileDTO(updated);
    }

    @PostMapping
    public void register(@Valid @RequestBody CreateUserDTO createUserDTO) {
        userService.create(createUserDTO.map());
    }

    @Secured({"WORKER","ADMINISTRATOR"})
    @PostMapping ("/search")
    public List<UserProfileDTO> getSearched(@RequestBody SearchUserDTO searchUserDTO) {
        String name = searchUserDTO.getName();
        String surname = searchUserDTO.getSurname();
        return userService.getSearchedUsers(name, surname);
    }

    @GetMapping("/first-login")
    public boolean getFirstLoginState() {
        return userService.checkFirstLoginAdmin(userUtils.getLoggedId());
    }
}
