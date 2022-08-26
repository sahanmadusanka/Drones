package com.drones.entity;

import com.drones.bean.drone.DroneModel;
import com.drones.bean.drone.DroneState;
import com.drones.bean.drone.DroneVo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "drone")
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;

    private Integer weightLimit;
    private LocalDateTime registeredDate;

    @OneToOne(mappedBy = "drone", cascade = CascadeType.ALL)
    private DroneStatus droneStatus;

    public Drone() {
    }

    public Drone(DroneVo droneVo) {
        this.serialNumber = droneVo.getSerialNumber();
        this.model = DroneModel.findByModel(droneVo.getModel());
        this.weightLimit = droneVo.getWeightLimit();
        this.registeredDate = LocalDateTime.now(ZoneOffset.UTC);

        this.droneStatus = new DroneStatus(this);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getModel() {
        return model;
    }

    public void setModel(DroneModel model) {
        this.model = model;
    }

    public Integer getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Integer weightLimit) {
        this.weightLimit = weightLimit;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }
}
