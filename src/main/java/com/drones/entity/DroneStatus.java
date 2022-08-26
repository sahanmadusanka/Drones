package com.drones.entity;

import com.drones.bean.drone.DroneState;

import javax.persistence.*;

@Entity
@Table(name = "drone_status")
public class DroneStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    private Drone drone;

    private Integer batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;

    public DroneStatus() {
    }

    public DroneStatus(Drone drone) {
        this.drone = drone;
        this.batteryCapacity = 0;
        this.state = DroneState.IDLE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public DroneState getState() {
        return state;
    }

    public void setState(DroneState state) {
        this.state = state;
    }
}
