package com.isateam.blooddonationcenter.database.users;

import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository {
    private final IUserDao repository;

    @Override
    public User getById(long id) {
        return convert(
                repository
                .findById(id)
                .orElseThrow()
        );
    }

    private User convert(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .surname(entity.getSurname())
                .uid(entity.getUid())
                .address(convert(entity.getAddress()))
                .sex(entity.getSex())
                .profession(entity.getProfession())
                .school(entity.getSchool())
                .build();

    }

    private UserEntity convert(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .uid(user.getUid())
                .address(convert(user.getAddress()))
                .sex(user.getSex())
                .profession(user.getProfession())
                .school(user.getSchool())
                .build();
    }

    private Address convert(AddressEntity entity) {
        return Address.builder()
                .street(entity.getStreet())
                .number(entity.getNumber())
                .city(entity.getCity())
                .country(entity.getCountry())
                .build();
    }

    private AddressEntity convert(Address address) {
        return AddressEntity.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
    }

}
