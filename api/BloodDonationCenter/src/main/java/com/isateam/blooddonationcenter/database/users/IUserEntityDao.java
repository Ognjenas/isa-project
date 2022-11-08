package com.isateam.blooddonationcenter.database.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IUserEntityDao extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
