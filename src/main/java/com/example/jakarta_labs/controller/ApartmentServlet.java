package com.example.jakarta_labs.controller;

import com.example.jakarta_labs.model.Apartment;
import com.example.jakarta_labs.model.ApartmentParameters;
import com.example.jakarta_labs.service.stub.ApartmentServiceStub; // Will be created later

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/apartments", "/apartment"})
public class ApartmentServlet extends HttpServlet {

    private ApartmentServiceStub apartmentService; // Use the stub service

    @Override
    public void init() {
        // Initialize the stub service
        apartmentService = new ApartmentServiceStub();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/apartments":
                    listApartments(request, response);
                    break;
                case "/apartment":
                    showApartmentDetailsOrForm(request, response);
                    break;
                default:
                    listApartments(request, response); // Default to listing apartments
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check for the DELETE method simulation first
        String method = request.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(request, response); // Delegate to doDelete
            return; // Stop further processing
        }

        String action = request.getParameter("action"); // Get action from form

        try {
            if (action == null) {
                // Handle case where the 'action' parameter is missing (e.g., log warning or redirect)
                System.err.println("Warning: 'action' parameter is missing in POST request to /apartment");
                response.sendRedirect(request.getContextPath() + "/apartments"); // Redirect to a safe page
                return; // Stop further processing
            }

            switch (action) {
                case "create":
                    createApartment(request, response);
                    break;
                case "update":
                    updateApartment(request, response);
                    break;
                default:
                    // Handle unknown action or redirect to a default page
                    response.sendRedirect(request.getContextPath() + "/apartments");
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Assuming DELETE requests will have an 'id' parameter
        int apartmentId = Integer.parseInt(request.getParameter("id"));
        apartmentService.deleteApartment(apartmentId); // Call stub delete method
        response.sendRedirect(request.getContextPath() + "/apartments"); // Redirect to list
    }

    private void listApartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Apartment> listApartments = apartmentService.getAllApartments(); // Get data from stub
        request.setAttribute("listApartments", listApartments); // Set as attribute for JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/apartments.jsp"); // Forward to JSP
        dispatcher.forward(request, response);
    }

    private void showApartmentDetailsOrForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String mode = request.getParameter("mode"); // e.g., "edit", "view", "create"

        if (idParam != null && !idParam.isEmpty()) {
            int apartmentId = Integer.parseInt(idParam);
            Apartment apartment = apartmentService.getApartmentById(apartmentId); // Get data from stub
            request.setAttribute("apartment", apartment); // Set as attribute for JSP

            RequestDispatcher dispatcher;// Forward to the details page
            if ("edit".equals(mode)) {
                dispatcher = request.getRequestDispatcher("WEB-INF/jsp/apartment_form.jsp");
            } else { // Default to view mode
                dispatcher = request.getRequestDispatcher("WEB-INF/jsp/apartment_details.jsp");
            }
            dispatcher.forward(request, response);
        } else if ("create".equals(mode)) {
            // For creating a new apartment, show the empty form
            request.setAttribute("apartment", new Apartment()); // Provide an empty apartment object
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/apartment_form.jsp"); // Forward to create form
            dispatcher.forward(request, response);
        } else {
            // Handle case where id and mode are missing (e.g., redirect to list)
            response.sendRedirect(request.getContextPath() + "/apartments");
        }
    }

    private void createApartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get data from form parameters
        String address = request.getParameter("address");
        double area = Double.parseDouble(request.getParameter("area"));
        int numberOfRooms = Integer.parseInt(request.getParameter("numberOfRooms"));
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        boolean hasBalcony = request.getParameter("hasBalcony") != null;
        boolean hasParking = request.getParameter("hasParking") != null;
        boolean isFurnished = request.getParameter("isFurnished") != null;
        int floor = Integer.parseInt(request.getParameter("floor"));

        // Create an ApartmentParameters object
        ApartmentParameters parameters = new ApartmentParameters(hasBalcony, hasParking, isFurnished, floor);

        // Create an Apartment object (ID will be assigned by the service later)
        Apartment newApartment = new Apartment();
        newApartment.setAddress(address);
        newApartment.setArea(area);
        newApartment.setNumberOfRooms(numberOfRooms);
        newApartment.setPrice(price);
        newApartment.setDescription(description);
        newApartment.setParameters(parameters);

        apartmentService.createApartment(newApartment); // Call stub-create method

        response.sendRedirect(request.getContextPath() + "/apartments"); // Redirect to list after creation
    }

    private void updateApartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get ID and data from form parameters
        int id = Integer.parseInt(request.getParameter("id"));
        String address = request.getParameter("address");
        double area = Double.parseDouble(request.getParameter("area"));
        int numberOfRooms = Integer.parseInt(request.getParameter("numberOfRooms"));
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");

        boolean hasBalcony = request.getParameter("hasBalcony") != null;
        boolean hasParking = request.getParameter("hasParking") != null;
        boolean isFurnished = request.getParameter("isFurnished") != null;
        int floor = Integer.parseInt(request.getParameter("floor"));

        // Create an ApartmentParameters object
        ApartmentParameters parameters = new ApartmentParameters(hasBalcony, hasParking, isFurnished, floor);

        // Create or update an Apartment object
        Apartment updatedApartment = new Apartment(id, address, area, numberOfRooms, price, description, parameters);

        apartmentService.updateApartment(updatedApartment); // Call stub update method

        response.sendRedirect(request.getContextPath() + "/apartment?id=" + id + "&mode=view"); // Redirect to details after update
    }
}
