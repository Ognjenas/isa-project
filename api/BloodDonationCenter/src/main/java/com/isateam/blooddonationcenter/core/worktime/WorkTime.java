package com.isateam.blooddonationcenter.core.worktime;


import com.isateam.blooddonationcenter.core.centers.Center;
import jdk.jfr.Timespan;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="work_time")
public class WorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "center_id", nullable = false)
    private Center center;

    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
}
