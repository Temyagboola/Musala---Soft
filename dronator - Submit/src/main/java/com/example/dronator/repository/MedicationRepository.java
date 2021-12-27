package com.example.dronator.repository;

import com.example.dronator.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {

    List<Medication> findMedicationByDroneDroneId(String droneId);


}
