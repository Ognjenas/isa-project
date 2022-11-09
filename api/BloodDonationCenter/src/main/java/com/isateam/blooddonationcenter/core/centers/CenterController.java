package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.dtos.AllCentersDto;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
