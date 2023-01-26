package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.users.dtos.*;
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

    @GetMapping("/me")
    public UserProfileDTO getMyself() {
        long id = userUtils.getLoggedId();
        User user = userService.getOne(id);
        return new UserProfileDTO(user);
    }

    @GetMapping("/{id}")
    public UserProfileDTO getOne(@PathVariable("id") long id) {
        User user = userService.getOne(id);
        return new UserProfileDTO(user);
    }

    @PatchMapping("")
    public UserProfileDTO updateOne(
            @Valid @RequestBody UpdateUserDTO user
    ) {
        user.setId(userUtils.getLoggedId());
        User updated = userService.updateOne(user);
        return new UserProfileDTO(updated);
    }

    @PatchMapping("/password")
    public boolean changePassword(@Valid @RequestBody ChangePasswordDTO dto){
        return userService.changePassword(dto,userUtils.getLoggedId());
    }

    @PostMapping
    public void register(@Valid @RequestBody CreateUserDTO createUserDTO) {
        userService.create(createUserDTO.map());
    }

    @Secured({"WORKER", "ADMINISTRATOR"})
    @PostMapping("/search")
    public List<UserProfileDTO> getSearched(@RequestBody SearchUserDTO searchUserDTO) {
        String name = searchUserDTO.getName();
        String surname = searchUserDTO.getSurname();
        return userService.getSearchedUsers(userUtils.getLoggedId(), name, surname);
    }

    @GetMapping("/first-login")
    public boolean getFirstLoginState() {
        return userService.checkFirstLoginAdmin(userUtils.getLoggedId());
    }


}
