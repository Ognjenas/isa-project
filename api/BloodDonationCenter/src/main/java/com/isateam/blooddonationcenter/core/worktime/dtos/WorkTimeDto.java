package com.isateam.blooddonationcenter.core.worktime.dtos;
import com.isateam.blooddonationcenter.core.worktime.WorkTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Comparator;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class WorkTimeDto {
//implements Comparator<WorkTimeDto>
    @NotNull
    @NotEmpty
    private Long id;

    @NotNull
    @NotEmpty
    private DayOfWeek day;

    @NotNull
    @NotEmpty
    private LocalTime startTime;

    @NotNull
    @NotEmpty
    private LocalTime endTime;

    public WorkTimeDto(WorkTime workTime){
        this.id= workTime.getId();
        this.day=workTime.getDay();
        this.startTime=workTime.getStartTime();
        this.endTime=workTime.getEndTime();
    }

//
//    @Override
//    public int compare(WorkTimeDto o1, WorkTimeDto o2) {
//        return o1.getDay().compareTo(o2.getDay());
//    }
}
