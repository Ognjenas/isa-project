package com.isateam.blooddonationcenter.core.rabbit;

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
