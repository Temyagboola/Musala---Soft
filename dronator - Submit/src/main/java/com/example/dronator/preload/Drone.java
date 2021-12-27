package com.example.dronator.preload;

import com.example.dronator.repository.DroneRepository;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDateTime;

public class Drone {
    CommandLineRunner commandLineRunner(DroneRepository droneRepository){
        return args -> {
            Drone firstDrone = new Drone(

            );
        };

    }
}
