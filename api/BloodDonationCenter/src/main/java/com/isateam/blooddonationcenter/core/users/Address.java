package com.isateam.blooddonationcenter.core.users;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Long id;
    private String street;
    private String number;
    private String city;
    private String country;
}
