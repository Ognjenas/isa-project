package com.isateam.blooddonationcenter.core.centers.dtos;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.worktime.WorkTime;
import com.isateam.blooddonationcenter.core.worktime.dtos.WorkTimeDto;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdateCenterDto {
    private long id;
    private String name;
    private String description;
    private Address address;
    private Set<WorkTimeDto> workTime;

    public Center mapToModel() {
        System.out.println(workTime);
        return Center.builder()
                .id(id)
                .address(address)
                .name(name)
                .description(description)
                .build();
    }
    public Set<WorkTime> mapWorkTimeToModel(Center validCenter){
        return workTime.stream().map(workTimeDto ->
                WorkTime.builder()
                        .id(workTimeDto.getId())
                        .day(workTimeDto.getDay())
                        .startTime(workTimeDto.getStartTime())
                        .endTime(workTimeDto.getEndTime())
                        .center(validCenter)
                        .build())
                .collect(Collectors.toSet());
    }
}
