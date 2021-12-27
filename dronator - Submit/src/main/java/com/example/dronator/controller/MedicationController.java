package com.example.dronator.controller;

import com.example.dronator.models.MedicationDto;
import com.example.dronator.service.DroneService;
import com.example.dronator.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;
    @Autowired
    private DroneService droneService;

    @PostMapping(value  = "/create/{droneId}", consumes = "multipart/form-data")
    public ResponseEntity<?> createMedication(@PathVariable String droneId,
                                              @Valid @RequestPart(value = "medication") MedicationDto medication,
                                              @RequestPart(value = "image", required = false) MultipartFile file){
        droneService.checkIfDroneCanBeLoaded(droneId, medication.getWeight());
        return new ResponseEntity<>(medicationService.addMedication(droneId, medication, file), HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateDetails(@RequestParam(value = "medicationId") String medicationId
            , @RequestBody Map<String, String> update){
        medicationService.updateMedication(medicationId, update);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(params = {"medicationId"})
    public ResponseEntity<?> viewDetails(@RequestParam(value = "medicationId") String medicationId){
        final var Medication = medicationService.getMedication(medicationId);
        if (Medication.isPresent()){
            return new ResponseEntity<>(Medication.get(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/for-drone")
    public ResponseEntity<?> createMedication(@RequestParam(value = "droneId") String droneId){
        return new ResponseEntity<>(medicationService.allMedicationsForDrone(droneId), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMedication(@RequestParam(value = "medicationId") String medicationId){
        medicationService.deleteMedication(medicationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
