package com.example.dronator.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "battery_health_trail")
public class DroneBatteryHealthTrail {

    @Id
    @Column(name = "trail_id")
    private String trailId;

    @Column(name = "drone_id")
    private String droneId;

    @Column(name = "battery_capacity")
    private Integer batteryCapacity;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public DroneBatteryHealthTrail() {
    }

    public DroneBatteryHealthTrail(String trailId, String droneId, Integer batteryCapacity) {
        this.trailId = trailId;
        this.droneId = droneId;
        this.batteryCapacity = batteryCapacity;
    }

    public String getTrailId() {
        return trailId;
    }

    public void setTrailId(String trailId) {
        this.trailId = trailId;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
    }

    public Integer getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(Integer batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
