package com.isateam.blooddonationcenter.core.bloodstorage;


import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.donations.BloodType;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "blood_storage")
public class BloodStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BloodType bloodType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "center_id", nullable = false)
    private Center center;

    private double quantity;

}
