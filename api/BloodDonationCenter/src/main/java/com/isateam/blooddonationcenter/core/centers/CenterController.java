package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CenterDto;
import com.isateam.blooddonationcenter.core.centers.dtos.CreateCenterDto;
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

    @PostMapping
    public Center createCenter(@RequestBody CreateCenterDto createCenterDto){
        System.out.println(createCenterDto.toString());
        return centerService.create(createCenterDto);
    }


    @PutMapping
    public CenterDto updateCenter(){
        return null;
    }
}
