package com.drones.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "drone_medication")
public class DroneMedication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="drone_id")
    private Drone drone;

    @ManyToOne
    @JoinColumn(name="medication_id")
    private Medication medication;

    private Integer quantity;

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

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DroneMedication that = (DroneMedication) o;
        return Objects.equals(id, that.id) && Objects.equals(drone, that.drone)
                && Objects.equals(medication, that.medication) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, drone, medication, quantity);
    }
}
