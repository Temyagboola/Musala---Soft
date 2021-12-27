package com.example.dronator.controller;

import com.example.dronator.models.DroneDto;
import com.example.dronator.models.enums.DroneState;
import com.example.dronator.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/drone")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/create")
    public ResponseEntity<?> createDrone(@RequestBody @Valid DroneDto drone){
        return new ResponseEntity<>(droneService.addDrone(drone), HttpStatus.CREATED);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateDetails(@RequestParam(value = "droneId") String droneId
            , @RequestBody Map<String, String> update){
        droneService.updateDrone(droneId, update);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/view-details", params = {"droneId"})
    public ResponseEntity<?> viewDetails(@RequestParam(value = "droneId") String droneId){
        final var drone = droneService.getDrone(droneId);
        if (drone.isPresent()){
            return new ResponseEntity<>(drone.get(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteDrone(@RequestParam(value = "droneId") String droneId){
        droneService.deleteDrone(droneId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/battery-capacity", params = {"droneId"})
    public ResponseEntity<?> batteryCapacity(@RequestParam(value = "droneId") String droneId){
        final var drone = droneService.batteryCapacityForDrone(droneId);
        if (drone.isPresent()){
            return new ResponseEntity<>(drone.get(), HttpStatus.OK);
        }else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-state", params = {"state"})
    public ResponseEntity<?> dronesByState(@RequestParam(value = "state") DroneState state){
        return new ResponseEntity<>(droneService.dronesByState(state), HttpStatus.OK);
    }
}
