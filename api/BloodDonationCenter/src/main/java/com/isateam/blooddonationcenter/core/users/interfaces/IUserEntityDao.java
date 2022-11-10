package com.isateam.blooddonationcenter.core.users.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isateam.blooddonationcenter.core.users.User;

import java.util.List;
import java.util.Optional;

public interface IUserEntityDao extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAllByNameLikeIgnoreCaseAndSurnameLikeIgnoreCase(String name,String surname);
}
