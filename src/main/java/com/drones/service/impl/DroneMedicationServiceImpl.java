package com.drones.service.impl;

import com.drones.bean.drone.DroneState;
import com.drones.bean.medication.MedLoadVo;
import com.drones.bean.medication.MedLoadRequestVo;
import com.drones.entity.Drone;
import com.drones.entity.DroneMedication;
import com.drones.entity.Medication;
import com.drones.exception.DataNotFoundException;
import com.drones.exception.DroneStatusException;
import com.drones.exception.InvalidInputException;
import com.drones.repository.DroneMedicationRepository;
import com.drones.service.DroneMedicationService;
import com.drones.service.DroneService;
import com.drones.service.MedicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DroneMedicationServiceImpl implements DroneMedicationService {

    private DroneMedicationRepository droneMedicationRepository;
    private DroneService droneService;
    private MedicationService medicationService;

    @Value("${drone.battery.loadingMinLevel:25}")
    private int loadingMinCapacity;

    public DroneMedicationServiceImpl(DroneMedicationRepository droneMedicationRepository, DroneService droneService,
                                      MedicationService medicationService) {
        this.droneMedicationRepository = droneMedicationRepository;
        this.droneService = droneService;
        this.medicationService = medicationService;
    }

    /**
     * Load medication to a drone
     *
     * @param serialNo
     * @param medicationCodes
     */
    @Transactional
    @Override
    public List<DroneMedication> loadToDrone(String serialNo, MedLoadRequestVo medicationCodes) {
        var drone = droneService.findBySerialNo(serialNo);
        validateDroneState(drone);
        validateDroneBatteryCapacity(drone);

        droneService.setDroneState(drone, DroneState.LOADING);

        var medCodes = medicationCodes.items()
                .stream()
                .map(MedLoadVo::code)
                .collect(Collectors.toSet());

        var medications = medicationService.findMedicationByCode(medCodes);
        validateOverloading(drone, medicationCodes.items(), medications);

        var droneMedications = createMedicationLoadList(drone, medications, medicationCodes);
        droneMedicationRepository.saveAll(droneMedications);

        droneService.setDroneState(drone, DroneState.LOADED);
        return droneMedications;
    }

    private void validateDroneState(Drone drone) {
        if (drone.getDroneStatus().getState() != DroneState.IDLE)
            throw new DroneStatusException("To load medication to the drone, the drone should be in an idle state");
    }

    private void validateDroneBatteryCapacity(Drone drone) {
        if (drone.getDroneStatus().getBatteryCapacity() < loadingMinCapacity)
            throw new DroneStatusException("Battery level is low to load medication onto the drone");
    }

    private void validateOverloading(Drone drone, List<MedLoadVo> medicationLoadList,
                                     Set<Medication> medications) {

        var loadedWeight = droneMedicationRepository.getTotalLoadedWeight(drone.getId());
        var currentWeight = medicationLoadList.stream()
                .map(med -> med.quantity() * getMedWeight(med.code(), medications))
                .mapToDouble(Double::valueOf).sum();

        var totalWeightInGrams = ((loadedWeight != null ? loadedWeight : 0) + currentWeight) / 1000; // Convert milligrams to grams

        if (totalWeightInGrams > drone.getWeightLimit()) {
            throw new InvalidInputException("Done overloaded with medication");
        }
    }

    private double getMedWeight(String medCode, Set<Medication> medications) {
        return medications.stream()
                .filter(med -> medCode.equals(med.getCode()))
                .findFirst()
                .orElseThrow(() -> new DataNotFoundException("Cannot find the medication with code " + medCode))
                .getWeight();
    }

    private List<DroneMedication> createMedicationLoadList(Drone drone, Set<Medication> medications,
                                                           MedLoadRequestVo medicationCodes) {
        List<DroneMedication> droneMedications = new ArrayList<>();

        for (MedLoadVo medicationLoad : medicationCodes.items()) {
            var droneMedication = new DroneMedication();
            var medication = medications.stream()
                    .filter(med -> medicationLoad.code().equals(med.getCode()))
                    .findFirst()
                    .orElseThrow(() -> new DataNotFoundException("Cannot find medication, Code:" + medicationLoad.code()));

            droneMedication.setDrone(drone);
            droneMedication.setMedication(medication);
            droneMedication.setQuantity(medicationLoad.quantity());

            droneMedications.add(droneMedication);
        }
        return droneMedications;
    }


}
