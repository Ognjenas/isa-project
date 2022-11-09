package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CreateCenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CriteriaDto;
import com.isateam.blooddonationcenter.core.centers.interfaces.CenterDao;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterCustomDao;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterService;
import com.isateam.blooddonationcenter.core.errorhandling.BadRequestException;
import com.isateam.blooddonationcenter.core.users.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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
    public Center create(CreateCenterDto createCenterDto) {
        Center center= new Center();
        Address address =createCenterDto.getAddress();
        address.setId(null);
        center.setAddress(address);
        center.setName(createCenterDto.getName());
        center.setDescription(createCenterDto.getDescription());
        center.setAverageGrade(0.);
        center.setWorkers(createCenterDto.getWorkers());
        return centerDao.save(center);
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
