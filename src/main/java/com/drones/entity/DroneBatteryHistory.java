package com.drones.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Entity
@Table(name = "drone_battery_history")
public class DroneBatteryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

    private Integer batteryCapacity;
    private LocalDateTime dateTime;

    public DroneBatteryHistory() {
    }

    public DroneBatteryHistory(Drone drone, int batteryCapacity) {
        this.drone = drone;
        this.batteryCapacity = batteryCapacity;
        this.dateTime = LocalDateTime.now(ZoneOffset.UTC);
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DroneBatteryHistory that = (DroneBatteryHistory) o;
        return Objects.equals(id, that.id) && Objects.equals(drone, that.drone)
                && Objects.equals(batteryCapacity, that.batteryCapacity) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, drone, batteryCapacity, dateTime);
    }
}
