package com.example.jakarta_labs.controller;

import com.example.jakarta_labs.model.Apartment;
import com.example.jakarta_labs.model.ApartmentParameters;
import com.example.jakarta_labs.service.stub.ApartmentServiceStub;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private ApartmentServiceStub apartmentService;

    @Override
    public void init() {
        apartmentService = new ApartmentServiceStub();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the search form
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/search_form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            // Get search parameters from the form

            // Get boolean parameters (checkboxes)
            boolean hasBalcony = request.getParameter("hasBalcony") != null;
            boolean hasParking = request.getParameter("hasParking") != null;
            boolean isFurnished = request.getParameter("isFurnished") != null;

            // Get numerical parameters and handle potential NumberFormatException
            int searchMinFloor = 0;
            String searchMinFloorParam = request.getParameter("searchMinFloor"); // Use new parameter name
            if (searchMinFloorParam != null && !searchMinFloorParam.isEmpty()) {
                try {
                    searchMinFloor = Integer.parseInt(searchMinFloorParam);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid searchMinFloor value: " + searchMinFloorParam);
                }
            }

            int searchMaxFloor = 0; // Default value for maxFloor, 0 can mean no upper limit
            String searchMaxFloorParam = request.getParameter("searchMaxFloor");
            if (searchMaxFloorParam != null && !searchMaxFloorParam.isEmpty()) {
                try {
                    searchMaxFloor = Integer.parseInt(searchMaxFloorParam);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid searchMaxFloor value: " + searchMaxFloorParam);
                }
            } else {
                searchMaxFloor = Integer.MAX_VALUE;
            }


            int searchNumberOfRooms = 0; // Default value
            String searchNumberOfRoomsParam = request.getParameter("searchNumberOfRooms");
            if (searchNumberOfRoomsParam != null && !searchNumberOfRoomsParam.isEmpty()) {
                try {
                    searchNumberOfRooms = Integer.parseInt(searchNumberOfRoomsParam);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid searchNumberOfRooms value: " + searchNumberOfRoomsParam);
                }
            }

            double searchMinPrice = 0.0; // Default value
            String searchMinPriceParam = request.getParameter("searchMinPrice");
            if (searchMinPriceParam != null && !searchMinPriceParam.isEmpty()) {
                try {
                    searchMinPrice = Double.parseDouble(searchMinPriceParam);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid searchMinPrice value: " + searchMinPriceParam);
                }
            }

            double searchMaxPrice = Double.MAX_VALUE; // Default to no upper limit
            String searchMaxPriceParam = request.getParameter("searchMaxPrice");
            if (searchMaxPriceParam != null && !searchMaxPriceParam.isEmpty()) {
                try {
                    searchMaxPrice = Double.parseDouble(searchMaxPriceParam);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid searchMaxPrice value: " + searchMaxPriceParam);
                }
            }


            // Create ApartmentParameters object for search using the constructor for search
            ApartmentParameters searchParameters = new ApartmentParameters(
                    hasBalcony,
                    hasParking,
                    isFurnished,
                    searchMinFloor,
                    searchMaxFloor,
                    searchNumberOfRooms,
                    searchMinPrice,
                    searchMaxPrice
            );

            // Perform search using the stub service
            List<Apartment> searchResults = apartmentService.searchApartments(searchParameters);

            // Set search results as an attribute for the JSP
            request.setAttribute("searchResults", searchResults);
            request.setAttribute("searchParameters", searchParameters);

            // Forward to the search results JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/search_results.jsp");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
