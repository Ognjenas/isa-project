package com.isateam.blooddonationcenter.core.workers;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.users.User;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "workers")
public class Worker {

    @Id
    @SequenceGenerator(name = "worker_seq",
            sequenceName = "worker_sequence",
            initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "worker_seq")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="center_id", nullable=false)
    private Center center;
}
