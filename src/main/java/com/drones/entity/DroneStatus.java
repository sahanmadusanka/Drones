package com.drones.entity;

import com.drones.bean.drone.DroneState;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "drone_status")
public class DroneStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    private Drone drone;

    @Min(0)
    @Max(100)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DroneStatus that = (DroneStatus) o;
        return Objects.equals(id, that.id) && Objects.equals(drone, that.drone)
                && Objects.equals(batteryCapacity, that.batteryCapacity) && state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, drone, batteryCapacity, state);
    }
}
