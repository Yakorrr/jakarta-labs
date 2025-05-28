package com.example.jakarta_labs.model;

public class ApartmentParameters {
    private boolean hasBalcony;
    private boolean hasParking;
    private boolean hasFurniture;
    private int floor;

    // Default constructor
    public ApartmentParameters() {
    }

    // Constructor with all fields
    public ApartmentParameters(boolean hasBalcony, boolean hasParking, boolean hasFurniture, int floor) {
        this.hasBalcony = hasBalcony;
        this.hasParking = hasParking;
        this.hasFurniture = hasFurniture;
        this.floor = floor;
    }

    // Getters and setters
    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public boolean isHasParking() {
        return hasParking;
    }

    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }

    public boolean isHasFurniture() {
        return hasFurniture;
    }

    public void setHasFurniture(boolean hasFurniture) {
        this.hasFurniture = hasFurniture;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "ApartmentParameters{" +
                "hasBalcony=" + hasBalcony +
                ", hasParking=" + hasParking +
                ", isFurnished=" + hasFurniture +
                ", floor=" + floor +
                '}';
    }
}
