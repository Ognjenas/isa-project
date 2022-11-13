package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CreateCenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CriteriaDto;
import com.isateam.blooddonationcenter.core.centers.interfaces.CenterDao;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterCustomDao;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterService;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.errorhandling.NotFoundException;
import com.isateam.blooddonationcenter.core.users.Address;
import com.isateam.blooddonationcenter.core.workers.interfaces.IWorkerDao;
import com.isateam.blooddonationcenter.core.worktime.WorkTime;
import com.isateam.blooddonationcenter.core.worktime.dtos.WorkTimeDto;
import com.isateam.blooddonationcenter.core.worktime.interfaces.IWorkTimeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterService implements ICenterService {

    private final CenterDao centerDao;

    private final ICenterCustomDao centerCustomDao;
    private final IWorkTimeDao workTimeDao;

    @Override
    public AllCentersDto getAll(Map<String, String> map) {
        return mapToAllCentersDto(centerCustomDao.getFiltered(map));
    }
    @Override
    public Center create(Center center) {
        return centerDao.save(center);
    }

    @Override
    public Center update(Center center) {
        Center centerOld = centerDao.findById(center.getId())
                .orElseThrow(() -> new NotFoundException("Center doesnt exist"));

        center.setAverageGrade(centerOld.getAverageGrade());
        return centerDao.save(center);
    }


    @Override
    public void addWorkTimeToCenter(Set<WorkTime> mapWorkTimeToModel) {
        workTimeDao.saveAll(mapWorkTimeToModel);
    }

    @Override
    public CenterDto getById(long id) {
        Center center=centerDao.findById(id).orElseThrow(() -> new NotFoundException("Center doesnt exist"));
        List<WorkTimeDto> workTimes= new ArrayList<>();
        center.getWorkTime().stream().forEach(workTime -> workTimes.add(new WorkTimeDto(workTime)));
        Collections.sort(workTimes,(a,b)-> { return a.getDay().compareTo(b.getDay()); });
        return CenterDto.builder()
                .address(center.getAddress())
                .name(center.getName())
                .description(center.getDescription())
                .averageGrade(center.getAverageGrade())
                .workTime(workTimes)
                .build();
    }


    private AllCentersDto mapToAllCentersDto(List<Center> centers) {
        return new AllCentersDto(centers.stream().map(c -> CenterDto.builder()
                .id(c.getId())
                .address(c.getAddress())
                .averageGrade(c.getAverageGrade())
                .description(c.getDescription())
                .name(c.getName())
                .build()).collect(Collectors.toList()));
    }

}
