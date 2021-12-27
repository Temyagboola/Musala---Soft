package com.example.dronator.preload;

import com.example.dronator.repository.DroneBatteryHealthTrailRepository;
import org.springframework.boot.CommandLineRunner;

public class BatteryHealthTrail {
    CommandLineRunner Batteryrunner(DroneBatteryHealthTrailRepository droneBatteryHealthTrailRepository){
        return args -> {
            BatteryHealthTrail firstTrail = new BatteryHealthTrail(

            );
        };
    }
}
