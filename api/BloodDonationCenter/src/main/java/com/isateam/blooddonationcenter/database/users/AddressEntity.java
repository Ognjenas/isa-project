package com.isateam.blooddonationcenter.database.users;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AddressEntity {
    @Id
    private Long id;
    private String street;
    private String number;
    private String city;
    private String country;
}
