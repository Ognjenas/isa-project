package com.isateam.blooddonationcenter.core.users.interfaces;

import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.UserValidation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserValidationDao extends JpaRepository<UserValidation, Long> {
    Optional<UserValidation> findByUuid(String user);
}
