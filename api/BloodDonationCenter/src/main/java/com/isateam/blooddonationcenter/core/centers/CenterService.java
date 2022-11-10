package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CenterDto;
import com.isateam.blooddonationcenter.core.centers.interfaces.CenterDao;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterService;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterService implements ICenterService {

    private final CenterDao centerDao;

    @Override
    public void create(Center center) {
        centerDao.save(center);
    }

    @Override
    public AllCentersDto getAll() {
        return new AllCentersDto(centerDao.findAll().stream().map(c -> CenterDto.builder()
                .address(c.getAddress())
                .averageGrade(c.getAverageGrade())
                .description(c.getDescription())
                .name(c.getName())
                .build()).collect(Collectors.toList()));
    }
}
