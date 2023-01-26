package com.isateam.blooddonationcenter.core.bloodcontracts;


import com.isateam.blooddonationcenter.core.blooddonations.BloodType;
import lombok.*;

import javax.persistence.*;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "blood_contracts")
public class BloodContract {

    @Id
    @SequenceGenerator(name = "blc_seq",
            sequenceName = "blc_sequence",
            initialValue = 3)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blc_seq")
    private Long id;

    private String email;

    private BloodType type;

    private double quantity;
    private String unit;

    private ContractState state;

    private long centerId;

}
