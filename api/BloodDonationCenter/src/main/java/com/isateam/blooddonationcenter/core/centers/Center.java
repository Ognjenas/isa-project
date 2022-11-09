package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.appointment.Appointment;
import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.workers.Worker;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "centers")
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    private Double averageGrade;

    private String description;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY)
    private Set<Worker> workers;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY)
    private Set<Appointment> appointments;


}
