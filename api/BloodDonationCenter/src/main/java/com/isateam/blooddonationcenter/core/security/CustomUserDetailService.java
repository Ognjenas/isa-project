package com.isateam.blooddonationcenter.core.security;

import com.isateam.blooddonationcenter.core.users.interfaces.IUserEntityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final IUserEntityDao userEntityDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userEntityDao.findByEmail(email).get();

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getGrantedAuthorities())
                .disabled(!user.isActivated())
                .build();
    }
}
