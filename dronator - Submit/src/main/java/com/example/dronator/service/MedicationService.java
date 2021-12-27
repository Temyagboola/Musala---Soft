package com.example.dronator.service;

import com.example.dronator.entities.Medication;
import com.example.dronator.models.MedicationDto;
import com.example.dronator.models.ModelsMapper;
import com.example.dronator.repository.MedicationRepository;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicationService {

    private final MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }


    public MedicationDto addMedication(String droneId, MedicationDto medicationDto, MultipartFile image){
        Medication medication = ModelsMapper.mapMedication(medicationDto);
        medication.setMedicationId(UUID.randomUUID().toString());
        try {
            medication.setImage(image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        medicationRepository.save(medication);
        return  medicationDto;
    }

    public Optional<MedicationDto> getMedication(String medicationId){
        return medicationRepository.findById(medicationId).map(ModelsMapper::mapMedication);
    }

    public void updateMedication(String medicationId, Map<String, String> update){
        final var existingMedication = getMedication(medicationId);
        if (existingMedication.isPresent()){
            try {
                BeanUtilsBean.getInstance().copyProperties(existingMedication, update);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            medicationRepository.save(ModelsMapper.mapMedication(existingMedication.get()));
        }
    }

    public void deleteMedication(String medicationId){
        medicationRepository.deleteById(medicationId);
    }

    public List<MedicationDto> allMedicationsForDrone(String droneId){
        return medicationRepository.findMedicationByDroneDroneId(droneId).stream()
                .map(ModelsMapper::mapMedication)
                .collect(Collectors.toList());
    }



}
