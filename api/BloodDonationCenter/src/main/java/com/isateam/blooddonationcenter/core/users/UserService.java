package com.isateam.blooddonationcenter.core.users;

import com.isateam.blooddonationcenter.core.users.interfaces.IUserRepository;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
}
