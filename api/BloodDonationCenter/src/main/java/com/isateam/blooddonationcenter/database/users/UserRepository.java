package com.isateam.blooddonationcenter.database.users;

import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.users.User;
import com.isateam.blooddonationcenter.core.users.interfaces.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository implements IUserRepository {

    private final IUserEntityDao repository;

    @Override
    public User getById(long id) throws NotFoundException{
        return convert(
                repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User with given id doesn't exist!"))
        );
    }

    @Override
    public void update(User user) {
        repository.save(convert(user));
    }

    @Override
    public User getByEmail(String email) {
        Optional<UserEntity> entity = repository.findByEmail(email);
        return entity.isEmpty() ? null : convert(entity.get());
    }

    @Override
    public User create(User user) {
        return convert(repository.save(convert(user)));
    }


    private User convert(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
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
                .password(user.getPassword())
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
                .id(entity.getId())
                .street(entity.getStreet())
                .number(entity.getNumber())
                .city(entity.getCity())
                .country(entity.getCountry())
                .build();
    }

    private AddressEntity convert(Address address) {
        return AddressEntity.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
    }

}
