package com.example.dronator.repository;

import com.example.dronator.entities.Drone;
import com.example.dronator.models.enums.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {

    List<Drone> findDronesByState(String state);

}
