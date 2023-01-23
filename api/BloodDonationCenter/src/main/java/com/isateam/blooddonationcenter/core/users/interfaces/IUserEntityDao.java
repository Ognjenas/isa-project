package com.isateam.blooddonationcenter.core.users.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isateam.blooddonationcenter.core.users.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IUserEntityDao extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    //List<User> findAllByNameLikeIgnoreCaseAndSurnameLikeIgnoreCase(String name,String surname);
    List<User> findAllByNameContainingIgnoreCaseAndSurnameContainingIgnoreCase(String name, String surname);
    List<User> findAllByNameContainingIgnoreCase(String name);
    List<User> findAllBySurnameContainingIgnoreCase(String surname);


    @Query(value = "select * from users where id in (select distinct(user_id) from appointments where center_id=?1 and state=2)", nativeQuery = true)
    List<User> findUsersThatDonatedInCenter(long centerId);

    @Query(value = "select * from users where id in " +
            "(select distinct(user_id) from appointments where center_id=?1 and state=2) " +
            "and LOWER(name) like LOWER(?2) and LOWER(surname) like LOWER(?3)  ", nativeQuery = true)
    List<User> findUsersThatDonatedInCenterBySearch(long centerId, String name, String surname);

}
