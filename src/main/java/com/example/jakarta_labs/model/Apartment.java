package com.example.jakarta_labs.model;

public class Apartment {
    private int id;
    private String address;
    private double area;
    private int numberOfRooms;
    private double price;
    private String description;
    private ApartmentParameters parameters;

    // Default constructor
    public Apartment() {
    }

    // Constructor with all fields
    public Apartment(int id, String address, double area, int numberOfRooms, double price, String description, ApartmentParameters parameters) {
        this.id = id;
        this.address = address;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.price = price;
        this.description = description;
        this.parameters = parameters;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApartmentParameters getParameters() {
        return parameters;
    }

    public void setParameters(ApartmentParameters parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", parameters=" + parameters +
                '}';
    }
}
