package com.example.jakarta_labs.model; // Package name

public class ApartmentParameters {
    private boolean hasBalcony;
    private boolean hasParking;
    private boolean hasFurniture;
    private int floor; // Actual floor of the apartment

    // Fields specifically for search criteria (can be ranges)
    private int minFloor; // Minimum floor for search
    private int maxFloor; // Maximum floor for search
    private int numberOfRooms; // Number of rooms for search (can represent exact or min)
    private double minPrice;
    private double maxPrice;


    // Default constructor
    public ApartmentParameters() {
    }

    // Constructor for creating/updating an actual Apartment object
    public ApartmentParameters(boolean hasBalcony, boolean hasParking, boolean hasFurniture, int floor) {
        this.hasBalcony = hasBalcony;
        this.hasParking = hasParking;
        this.hasFurniture = hasFurniture;
        this.floor = floor;
    }

    // Constructor for search parameters (includes search ranges)
    public ApartmentParameters(boolean hasBalcony, boolean hasParking, boolean hasFurniture, int minFloor, int maxFloor, int numberOfRooms, double minPrice, double maxPrice) {
        this.hasBalcony = hasBalcony;
        this.hasParking = hasParking;
        this.hasFurniture = hasFurniture;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        this.numberOfRooms = numberOfRooms;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    // Getters and setters (for actual parameters)
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

    // Getters and setters (for search parameters)
    public int getMinFloor() {
        return minFloor;
    }

    public void setMinFloor(int minFloor) {
        this.minFloor = minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(int maxFloor) {
        this.maxFloor = maxFloor;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }


    @Override
    public String toString() {
        return "ApartmentParameters{" +
                "hasBalcony=" + hasBalcony +
                ", hasParking=" + hasParking +
                ", hasFurniture=" + hasFurniture +
                ", floor=" + floor +
                '}';
    }
}
