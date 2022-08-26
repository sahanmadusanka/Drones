package com.drones.bean.drone;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class DroneVo {

    @NotBlank(message = "Serial Number is mandatory")
    @Size(max = 100, message = "Serial number cannot exceed 100 characters")
    private String serialNumber;

    @NotBlank(message = "Drone Model is mandatory")
    private String model;

    @Min(value = 10, message = "Drone need to carry at least 10g")
    private Integer weightLimit;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getWeightLimit() {
        return weightLimit;
    }

    /**
     * Set weight in grams (g)
     * @param weightLimit
     */
    public void setWeightLimit(Integer weightLimit) {
        this.weightLimit = weightLimit;
    }
}
