package com.isateam.blooddonationcenter.core.utils.session;

import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {

    private final IUserService userService;

    public long getLoggedId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var detailUser = (User)auth.getPrincipal();
        var user = userService.getByEmail(detailUser.getUsername());
        return user.getId();
    }
}
