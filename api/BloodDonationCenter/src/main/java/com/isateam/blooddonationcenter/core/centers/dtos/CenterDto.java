package com.isateam.blooddonationcenter.core.centers.dtos;

import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.worktime.WorkTime;
import com.isateam.blooddonationcenter.core.worktime.dtos.WorkTimeDto;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CenterDto {
    private long id;
    private String name;
    private String description;
    private double averageGrade;
    private Address address;
    private List<WorkTimeDto> workTime;
}
