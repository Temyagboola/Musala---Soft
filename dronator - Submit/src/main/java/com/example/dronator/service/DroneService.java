package com.example.dronator.service;

import com.example.dronator.entities.Drone;
import com.example.dronator.models.DroneDto;
import com.example.dronator.models.MedicationDto;
import com.example.dronator.models.ModelsMapper;
import com.example.dronator.models.enums.DroneState;
import com.example.dronator.models.exceptions.NotAllowedOperation;
import com.example.dronator.repository.DroneRepository;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DroneService {

    private final DroneRepository droneRepository;
    private final MedicationService medicationService;


    public DroneService(DroneRepository droneRepository,
                        MedicationService medicationService) {
        this.droneRepository = droneRepository;
        this.medicationService = medicationService;
    }

    public DroneDto addDrone(DroneDto DroneDto){
        Drone drone = ModelsMapper.mapDrone(DroneDto);
        drone.setDroneId(UUID.randomUUID().toString());
        droneRepository.save(drone);
        return  DroneDto;
    }

    public Optional<DroneDto> getDrone(String DroneId){
        return droneRepository.findById(DroneId).map(ModelsMapper::mapDrone);
    }

    public void updateDrone(String DroneId, Map<String, String> update){
        final var existingDrone = getDrone(DroneId);
        if (existingDrone.isPresent()){
            try {
                BeanUtilsBean.getInstance().copyProperties(existingDrone, update);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            droneRepository.save(ModelsMapper.mapDrone(existingDrone.get()));
        }
    }

    public void deleteDrone(String DroneId){
        droneRepository.deleteById(DroneId);
    }

    public Optional<Integer> batteryCapacityForDrone(String droneId){
        return getDrone(droneId).map(DroneDto::getBatteryCapacity);
    }

    public List<DroneDto> dronesByState(DroneState state){
        return droneRepository.findDronesByState(state.name()).stream().map(ModelsMapper::mapDrone)
                .collect(Collectors.toList());
    }

    public void checkIfDroneCanBeLoaded(String droneId, Double intendedWeight){
        final var drone = getDrone(droneId);
        if (drone.isPresent()){
            final var existingWeight = medicationService.allMedicationsForDrone(droneId)
                    .stream().map(MedicationDto::getWeight).reduce(0.0, Double::sum);
            if (drone.get().getBatteryCapacity() < 25){
                throw new NotAllowedOperation("You cannot load a drone with battery capacity below 25 ");
            } else if (drone.get().getWeightLimit().compareTo(existingWeight + intendedWeight) < 0){
                throw new NotAllowedOperation("Weight limit for drone exceeded");
            }
        } else throw new NotAllowedOperation("You cannot load a drone that does not exist");
    }


}
