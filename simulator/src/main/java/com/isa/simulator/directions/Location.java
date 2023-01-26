package com.isa.simulator.directions;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {
    private double longitude;
    private double latitude;
}
