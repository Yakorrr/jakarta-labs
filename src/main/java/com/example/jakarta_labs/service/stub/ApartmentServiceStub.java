package com.example.jakarta_labs.service.stub;

import com.example.jakarta_labs.model.Apartment;
import com.example.jakarta_labs.model.ApartmentParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ApartmentServiceStub {

    private final List<Apartment> apartments;
    private final AtomicInteger nextId;

    public ApartmentServiceStub() {
        // Initialize with some placeholder data
        apartments = new ArrayList<>();
        nextId = new AtomicInteger(1);

        // Create some sample apartments
        ApartmentParameters params1 = new ApartmentParameters(true, false, true, 3);
        Apartment apartment1 = new Apartment(nextId.getAndIncrement(), "Shevchenka St, 10", 65.5, 3, 500.0, "Cozy apartment in the city center.", params1);

        ApartmentParameters params2 = new ApartmentParameters(false, true, false, 1);
        Apartment apartment2 = new Apartment(nextId.getAndIncrement(), "Franko St, 25A", 40.0, 1, 350.0, "Studio apartment with parking.", params2);

        ApartmentParameters params3 = new ApartmentParameters(true, true, true, 5);
        Apartment apartment3 = new Apartment(nextId.getAndIncrement(), "Lesi Ukrainky Blvd, 5", 80.0, 4, 700.0, "Spacious apartment with balcony and parking.", params3);

        apartments.add(apartment1);
        apartments.add(apartment2);
        apartments.add(apartment3);
    }

    /**
     * Retrieves all apartments.
     * This is a stub method.
     *
     * @return A list of all apartments.
     */
    public List<Apartment> getAllApartments() {
        // Return a copy to avoid direct modification of the internal list
        return new ArrayList<>(apartments);
    }

    /**
     * Retrieves an apartment by its ID.
     * This is a stub method.
     *
     * @param id The ID of the apartment to retrieve.
     * @return The apartment with the given ID, or null if not found.
     */
    public Apartment getApartmentById(int id) {
        for (Apartment apartment : apartments) {
            if (apartment.getId() == id) {
                return apartment;
            }
        }
        return null; // Not found
    }

    /**
     * Creates a new apartment.
     * This is a stub method.
     *
     * @param apartment The apartment to create.
     */
    public void createApartment(Apartment apartment) {
        // Assign a new ID and add to the list
        apartment.setId(nextId.getAndIncrement());
        apartments.add(apartment);
        System.out.println("STUB: Created apartment: " + apartment.getId() + " - " + apartment.getAddress());
    }

    /**
     * Updates an existing apartment.
     * This is a stub method.
     *
     * @param apartment The apartment with updated information.
     */
    public void updateApartment(Apartment apartment) {
        // Find the apartment and update it
        for (int i = 0; i < apartments.size(); i++) {
            if (apartments.get(i).getId() == apartment.getId()) {
                apartments.set(i, apartment);
                System.out.println("STUB: Updated apartment: " + apartment.getId() + " - " + apartment.getAddress());
                return;
            }
        }
        System.out.println("STUB: Apartment not found for update: " + apartment.getId());
    }

    /**
     * Deletes an apartment by its ID.
     * This is a stub method.
     *
     * @param id The ID of the apartment to delete.
     */
    public void deleteApartment(int id) {
        String address = getApartmentById(id).getAddress();
        apartments.removeIf(apartment -> apartment.getId() == id);
        System.out.println("STUB: Deleted apartment: " + id + " - " + address);
    }

    /**
     * Searches for apartments based on parameters.
     * This is a stub method with basic filtering logic.
     *
     * @param searchParameters The parameters to search by.
     * @return A list of apartments matching the search criteria.
     */
    public List<Apartment> searchApartments(ApartmentParameters searchParameters) {
        // Implement basic filtering logic based on the provided parameters
        return apartments.stream()
                .filter(apartment -> {
                    ApartmentParameters currentParams = apartment.getParameters();
                    // Basic matching logic - you can make this more sophisticated
                    boolean matches = !searchParameters.isHasBalcony() || currentParams.isHasBalcony();
                    if (searchParameters.isHasParking() && !currentParams.isHasParking()) {
                        matches = false;
                    }
                    if (searchParameters.isHasFurniture() && !currentParams.isHasFurniture()) {
                        matches = false;
                    }
                    // Simple floor check (greater than or equal to)
                    if (searchParameters.getFloor() > 0 && currentParams.getFloor() < searchParameters.getFloor()) {
                        matches = false;
                    }
                    return matches;
                })
                .collect(Collectors.toList());
    }
}
