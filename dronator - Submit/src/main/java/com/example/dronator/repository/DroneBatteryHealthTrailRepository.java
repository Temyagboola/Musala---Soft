package com.example.dronator.repository;

import com.example.dronator.entities.DroneBatteryHealthTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBatteryHealthTrailRepository extends JpaRepository<DroneBatteryHealthTrail, String> {

}
