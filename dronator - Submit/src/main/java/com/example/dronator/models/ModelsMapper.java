package com.example.dronator.models;

import com.example.dronator.entities.Drone;
import com.example.dronator.entities.Medication;
import com.example.dronator.models.enums.DroneModel;
import com.example.dronator.models.enums.DroneState;

public class ModelsMapper {

    public static Drone mapDrone(DroneDto dto){
        Drone drone = new Drone();
        drone.setBatteryCapacity(dto.getBatteryCapacity());
        drone.setModel(dto.getModel().name());
        drone.setSerialNumber(dto.getSerialNumber());
        drone.setState(dto.getState().name());
        drone.setWeightLimit(dto.getWeightLimit());
        return drone;
    }

    public static DroneDto mapDrone(Drone entity){
        DroneDto droneDto = new DroneDto();
        droneDto.setDroneId(entity.getDroneId());
        droneDto.setBatteryCapacity(entity.getBatteryCapacity());
        droneDto.setModel(DroneModel.valueOf(entity.getModel()));
        droneDto.setSerialNumber(entity.getSerialNumber());
        droneDto.setState(DroneState.valueOf(entity.getState()));
        droneDto.setWeightLimit(entity.getWeightLimit());
        return droneDto;
    }

    public static Medication mapMedication(MedicationDto dto){
        Medication medication = new Medication();
        medication.setCode(dto.getCode());
        medication.setName(dto.getName());
        medication.setWeight(dto.getWeight());
        return  medication;
    }

    public static MedicationDto mapMedication(Medication entity){
        MedicationDto medicationDto = new MedicationDto();
        medicationDto.setMedicationId(entity.getMedicationId());
        medicationDto.setCode(entity.getCode());
        medicationDto.setName(entity.getName());
        medicationDto.setWeight(entity.getWeight());
        return  medicationDto;
    }


}
