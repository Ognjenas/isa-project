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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterService implements ICenterService {

    private final CenterDao centerDao;

    private final ICenterCustomDao centerCustomDao;

    @Override
    public AllCentersDto getAll(Map<String, String> map) {
        return mapToAllCentersDto(centerDao.findAll());
    }

    @Override
    public AllCentersDto sort(String field, String sort) {
        String[] fields = {"description", "name", "averageGrade", "city", "country", "number", "street"};
        if (Arrays.stream(fields).noneMatch(f -> f.equals(field))) {
            throw new BadRequestException("Given field does not exist");
        }
        if (!sort.equals("asc") && !sort.equals("desc")) {
            throw new BadRequestException("Given sort type not supported");
        }

        List<Center> centers = centerCustomDao.getSorted(field, sort);
        return mapToAllCentersDto(centers);
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
    public CenterDto getById(long id) {
        Center center=centerDao.findById(id).orElseThrow(() -> new NotFoundException("Center doesnt exist"));
        return CenterDto.builder()
                .address(center.getAddress())
                .name(center.getName())
                .description(center.getDescription())
                .averageGrade(center.getAverageGrade())
                .build();
    }

    private AllCentersDto mapToAllCentersDto(List<Center> centers) {
        return new AllCentersDto(centers.stream().map(c -> CenterDto.builder()
                .address(c.getAddress())
                .averageGrade(c.getAverageGrade())
                .description(c.getDescription())
                .name(c.getName())
                .build()).collect(Collectors.toList()));
    }

}
