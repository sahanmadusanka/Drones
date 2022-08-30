package com.drones.service.impl;

import com.drones.entity.Medication;
import com.drones.exception.DataIntegrityException;
import com.drones.repository.MedicationRepository;
import com.drones.service.MedicationService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MedicationServiceImpl implements MedicationService {

    private MedicationRepository medicationRepository;

    MedicationServiceImpl(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    /**
     * Get list of Medications with medication coeds
     *
     * @param medicationCodes
     * @return Set<Medication>
     */
    @Override
    public Set<Medication> findMedicationByCode(Set<String> medicationCodes) {
        var medications = medicationRepository.findByCodeIn(medicationCodes);
        if(medicationCodes.size() != medications.size()){
            throw new DataIntegrityException("Input codes not matching with DB records");
        }
        return medications;
    }
}
