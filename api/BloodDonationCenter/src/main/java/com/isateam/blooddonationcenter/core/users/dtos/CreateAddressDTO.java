package com.isateam.blooddonationcenter.core.users.dtos;

import com.isateam.blooddonationcenter.core.users.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateAddressDTO {
    @NotNull
    @NotEmpty
    private String street;
    @NotNull
    @NotEmpty
    private String number;
    @NotNull
    @NotEmpty
    private String city;
    @NotNull
    @NotEmpty
    private String country;

    public Address map() {
        return Address.builder()
                .id(null)
                .city(city)
                .country(country)
                .number(number)
                .street(street)
                .build();
    }
}
