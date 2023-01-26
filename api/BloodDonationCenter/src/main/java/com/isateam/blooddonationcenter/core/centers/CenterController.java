package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CreateCenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.UpdateCenterDto;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterService;
import com.isateam.blooddonationcenter.core.utils.session.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/centers")
@RequiredArgsConstructor
public class CenterController {

    private final ICenterService centerService;

    private final UserUtils userUtils;

    @GetMapping("/list")
    public AllCentersDto getAll(@RequestParam  Map<String, String> queryParams) {
        return centerService.getAll(queryParams);
    }
    @GetMapping("/{id}")
    public CenterDto getById(@PathVariable long id) {
        return centerService.getById(id,userUtils.getLoggedId());
    }

    @PostMapping
    public Center createCenter(@RequestBody CreateCenterDto createCenterDto) {
        return centerService.create(createCenterDto.mapToModel());
    }

    @PutMapping
    public Center updateCenter(@RequestBody UpdateCenterDto updateCenterDto) {
        Center center=centerService.update(updateCenterDto.mapToModel());
        centerService.addWorkTimeToCenter(updateCenterDto.mapWorkTimeToModel(center));
        return center;
    }

    @GetMapping("/by-time/{startTime}")
    public AllCentersDto getAllForPatientAppointmentCreation(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime startTime) {
        return centerService.getAllWithoutAppointment(startTime);
    }
}
