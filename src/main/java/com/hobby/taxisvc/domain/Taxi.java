package com.hobby.taxisvc.domain;

import java.util.Objects;

public class Taxi {
    String vehicleID;
    String ownerName;
    Integer capacity;

    public Taxi() {
    }

    public Taxi(String vehicleID, String ownerName, Integer capacity) {
        this.vehicleID = vehicleID;
        this.ownerName = ownerName;
        this.capacity = capacity;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taxi taxi = (Taxi) o;
        return Objects.equals(vehicleID, taxi.vehicleID) && Objects.equals(ownerName, taxi.ownerName) && Objects.equals(capacity, taxi.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleID, ownerName, capacity);
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "vehicleID='" + vehicleID + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
