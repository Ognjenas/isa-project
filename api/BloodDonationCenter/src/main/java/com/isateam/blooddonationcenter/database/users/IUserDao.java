package com.isateam.blooddonationcenter.database.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<UserEntity, Long> {
}
