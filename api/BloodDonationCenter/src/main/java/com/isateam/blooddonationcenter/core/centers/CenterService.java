package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CriteriaDto;
import com.isateam.blooddonationcenter.core.centers.interfaces.CenterDao;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterCustomDao;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterService;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CenterService implements ICenterService {

    private final CenterDao centerDao;

    private final ICenterCustomDao centerCustomDao;

    @Override
    public AllCentersDto getAll() {
        return mapToAllCentersDto(centerDao.findAll());
    }

    @Override
    public AllCentersDto sort(String field, String sort) {
        if (!field.equals("description") && !field.equals("name") && !field.equals("averageGrade")
                && !field.equals("city") && !field.equals("country")
                && !field.equals("number") && !field.equals("street")) {
            throw new BadRequestException("Given field does not exist");
        }
        if (!sort.equals("asc") && !sort.equals("desc")) {
            throw new BadRequestException("Given sort type not supported");
        }

        List<Center> centers = centerCustomDao.getSorted(field, sort);
        return mapToAllCentersDto(centers);
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
