package com.example.dronator.config;

import com.example.dronator.entities.DroneBatteryHealthTrail;
import com.example.dronator.repository.DroneBatteryHealthTrailRepository;
import com.example.dronator.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Scheduler {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private DroneBatteryHealthTrailRepository droneBatteryHealthTrailRepository;

    @Scheduled(cron = "*/10 * * * *")
    private void createAuditTrail() {
        droneRepository.findAll().forEach(drone -> {
            DroneBatteryHealthTrail trail = new DroneBatteryHealthTrail(UUID.randomUUID().toString(),
                    drone.getDroneId(), drone.getBatteryCapacity());
            droneBatteryHealthTrailRepository.save(trail);
        });

    }


}
