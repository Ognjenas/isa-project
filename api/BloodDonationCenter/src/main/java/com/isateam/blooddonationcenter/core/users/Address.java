package com.isateam.blooddonationcenter.core.users;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {
    @Id
    @SequenceGenerator(name = "address_seq",
            sequenceName = "address_sequence",
            initialValue = 5)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    private Long id;
    private String street;
    private String number;
    private String city;
    private String country;
}
