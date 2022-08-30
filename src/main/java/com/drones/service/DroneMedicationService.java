package com.drones.service;

import com.drones.bean.medication.MedItemVo;
import com.drones.bean.medication.MedLoadRequestVo;
import com.drones.entity.DroneMedication;

import java.util.List;

public interface DroneMedicationService {

    /**
     * Load medication to a drone
     *
     * @param serialNo
     * @param medicationCodes
     */
    List<DroneMedication> loadToDrone(String serialNo, MedLoadRequestVo medicationCodes);


    /**
     * Get loaded medication with drone serial number
     * @param droneSerialNo
     * @return MedLoadedResponseVo
     */
    List<MedItemVo> getLoadedMedication(String droneSerialNo);
}
