package com.isa.simulator.directions;

import com.isa.simulator.rabbit.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class DirectionServiceImpl implements DirectionService {

    private final QueueService queueService;

    private static Queue<Location> locations = new ArrayDeque<>();

    @Override
    public void addDirection(int seconds) {
        locations.addAll(List.of(new Location(45.273252, 19.782898),
                new Location(45.274540, 19.785869),
                new Location(45.278574, 19.790599),
                new Location(45.278019, 19.795399),
                new Location(45.275574, 19.800487),
                new Location(45.272171, 19.800664),
                new Location(45.268666, 19.802778),
                new Location(45.265593, 19.804904),
                new Location(45.261953, 19.807552),
                new Location(45.259675, 19.809078),
                new Location(45.256121, 19.811479),
                new Location(45.253044, 19.813450),
                new Location(45.249099, 19.818437),
                new Location(45.249142, 19.826195),
                new Location(45.249316, 19.830259),
                new Location(45.250790, 19.834816),
                new Location(45.250660, 19.834693),
                new Location(45.252524, 19.836540),
                new Location(45.254431, 19.835432),
                new Location(45.255168, 19.834939),
                new Location(45.254994, 19.832969)));

        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("radi");
                if(locations.size() == 0) {
                    return;
                }
                queueService.sendLocation(locations.poll());
            }
        }, 0, seconds, TimeUnit.SECONDS);
    }
}
