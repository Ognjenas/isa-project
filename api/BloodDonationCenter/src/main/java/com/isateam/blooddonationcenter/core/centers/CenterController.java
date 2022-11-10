package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CreateCenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.UpdateCenterDto;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/centers")
@RequiredArgsConstructor
public class CenterController {

    private final ICenterService centerService;

    @GetMapping
    public AllCentersDto getAll() {
        return centerService.getAll();
    }

    @GetMapping("/{field}/{sort}")
    public AllCentersDto getAll(@PathVariable String field, @PathVariable String sort) {
        return centerService.sort(field, sort);
    }

    @GetMapping("/{id}")
    public CenterDto getById(@PathVariable long id) {
        return centerService.getById(id);
    }

    @PostMapping
    public Center createCenter(@RequestBody CreateCenterDto createCenterDto) {
        return centerService.create(createCenterDto.mapToModel());
    }

    @PutMapping
    public Center updateCenter(@RequestBody UpdateCenterDto updateCenterDto) {
        return centerService.update(updateCenterDto.mapToModel());
    }


}
