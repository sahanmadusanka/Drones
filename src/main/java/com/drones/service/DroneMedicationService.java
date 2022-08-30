package com.drones.service;

import com.drones.bean.medication.MedLoadRequestVo;
import com.drones.entity.DroneMedication;

import java.util.List;

public interface DroneMedicationService {

    List<DroneMedication> loadToDrone(String serialNo, MedLoadRequestVo medicationCodes);
}
