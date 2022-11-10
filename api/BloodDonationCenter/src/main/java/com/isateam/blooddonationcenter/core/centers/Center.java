package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.appointments.Appointment;
import com.isateam.blooddonationcenter.core.bloodstorage.BloodStorage;
import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.workers.Worker;
import com.isateam.blooddonationcenter.core.worktime.WorkTime;
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

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Worker> workers;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<WorkTime> workTime;

    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY)
    private Set<BloodStorage> bloodStorage;

}
