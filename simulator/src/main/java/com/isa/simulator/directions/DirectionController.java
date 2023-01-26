package com.isa.simulator.directions;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("directions")
public class DirectionController {

    private final DirectionService directionService;

    @GetMapping("/{seconds}")
    public void add(@PathVariable int seconds) {
        directionService.addDirection(seconds);
    }
}
