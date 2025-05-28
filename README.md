# Apartment Rental Web Application (Presentation Layer with Stub Data)

This project is a web application for apartment rentals, built using Jakarta EE technologies (Servlet API, JSP, EL,
JSTL) and following the Model-View-Controller (MVC) architectural pattern. This implementation focuses on the
Presentation Layer (View - JSP + EL/JSTL, Controller - Servlets) and uses stub (dummy) data to simulate the Business
Logic and Data Access Layers.

## Project Goals for this Stage (Lab Work 2)

* Implement the Presentation Layer of the web application.
* Adhere to the MVC design pattern.
* Generate content on the server and send HTML to the client.
* Design interfaces and data structures for future integration with Business Logic and Data Access Layers.
* Provide stub data for demonstration.
* Utilize JSTL conditional formatting (`<c:if>`), selection (`<c:choose>/<c:when>`), and looping (`<c:forEach>`).
* Ensure the site is resilient to XSS attacks.

## Technology Stack

* **Backend:** Jakarta EE (Servlet API)
* **Frontend (Templating):** JSP, Expression Language (EL), JSTL
* **Build Tool:** Maven (assumed)
* **Server:** Apache Tomcat 10+ (supporting Jakarta EE 9+) or compatible Jakarta EE server.
* **Styling:** CSS

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── jakarta_labs
│   │   │               ├── controller
│   │   │               │   ├── ApartmentServlet.java
│   │   │               │   └── SearchServlet.java
│   │   │               └── apartments
│   │   │                   └── model
│   │   │                       ├── Apartment.java
│   │   │                       ├── ApartmentParameters.java
│   │   │                       └── ApartmentServiceStub.java  (Stub Service)
│   │   └── webapp
│   │       ├── css
│   │       │   └── styles.css      (External Stylesheet)
│   │       ├── WEB-INF
│   │       │   ├── jsp           (Might not be used based on JSP paths)
│   │       │   │   ├── apartment_details.jsp
│   │       │   │   ├── apartment_form.jsp
│   │       │   │   ├── apartments.jsp
│   │       │   │   ├── search_form.jsp
│   │       │   │   └── search_results.jsp
│   │       │   └── web.xml         (Deployment Descriptor)
│   │       └── index.jsp         (Entry point, redirects)
└── pom.xml             (Maven Project File)
└── README.md           (This file)
```

## Key Components

* **Model:**
    * `Apartment.java`: Represents a rental apartment with properties like address, area, price, etc. Includes an
      `ApartmentParameters` object.
    * `ApartmentParameters.java`: Represents additional parameters of an apartment (balcony, parking, furniture, floor).
      Modified to include search criteria fields (min/max price, min/max floor, number of rooms).
    * `ApartmentServiceStub.java`: A stub class simulating the Business Logic and Data Access Layers. It holds a list of
      dummy `Apartment` objects in memory and provides methods for CRUD operations and searching, based on the
      `ApartmentParameters` object (used for search criteria).

* **View (JSP + EL/JSTL):** Located in `src/main/webapp/WEB-INF/pages/`.
    * `apartments.jsp`: Displays a list of all apartments using `<c:forEach>`, showing key information. Includes a link
      to the search form. Links to view apartment details are always visible. Links/forms for editing/deleting are
      included but were previously conditionally displayed for owners (now removed).
    * `apartment_details.jsp`: Displays detailed information about a single apartment. Uses EL to access object
      properties and `<c:if>` / `<c:choose>` for conditional display (e.g., "Yes/No" for boolean parameters).
      Edit/delete links/forms were previously conditionally displayed for owners (now removed).
    * `apartment_form.jsp`: A form for creating or editing apartment information. Uses HTML form elements and EL to
      pre-fill data for editing. Submits data via POST to `ApartmentServlet`.
    * `search_form.jsp`: A form for entering search criteria (price range, floor range, number of rooms, boolean
      parameters). Uses HTML form elements and submits data via POST to `SearchServlet`.
    * `search_results.jsp`: Displays the list of apartments that match the search criteria. Uses `<c:forEach>` to
      iterate over the results and displays information similar to `apartments.jsp`. Links to view details are provided.
      Edit/delete links/forms were previously conditionally displayed for owners (now removed).
    * `index.jsp`: The project's entry point, which simply redirects to the `/apartments` URL to display the list of
      apartments.
    * `styles.css`: An external CSS file located in `src/main/webapp/css/` for consistent styling across pages with a
      modern blue/cyan color scheme.

* **Controller (Servlets):** Located in `src/main/java/com/example/jakarta_labs/controller/`.
    * `ApartmentServlet.java`: Handles requests related to viewing the list of apartments, viewing details, showing
      create/edit forms, and processing create/update/delete actions (simulated via POST with `_method` parameter for
      DELETE). Interacts with `ApartmentServiceStub`.
    * `SearchServlet.java`: Handles requests related to displaying the search form (`doGet`) and processing search
      submissions (`doPost`). It receives search parameters from the form, calls the `ApartmentServiceStub` to "search"
      for apartments, and forwards the results to `search_results.jsp`.

* **Deployment Descriptor (`web.xml`):** Configures the web application, mapping URLs to Servlets and defining the
  welcome file. Located in `src/main/webapp/WEB-INF/`.

## Setup and Running

1. **Prerequisites:**
    * Java Development Kit (JDK) 17+
    * Apache Maven
    * An IDE that supports Jakarta EE web development (e.g., IntelliJ IDEA Ultimate, Eclipse IDE for Enterprise Java and
      Web Developers)
    * An Apache Tomcat 10+ server or compatible Jakarta EE Web Profile server.

2. **Clone or Download:** Get the project code.

3. **Import into IDE:** Import the project into your IDE as a Maven project.

4. **Add JSTL Dependency:** Ensure you have the necessary JSTL dependencies in your `pom.xml`:

   ```xml
   <dependency>
       <groupId>jakarta.servlet.jsp.jstl</groupId>
       <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
       <version>3.0.0</version>
   </dependency>
   <dependency>
       <groupId>org.glassfish.web</groupId>
       <artifactId>jakarta.servlet.jsp.jstl</artifactId>
       <version>3.0.1</version>
       <scope>runtime</scope>
   </dependency>
   ```

5. **Configure Server in IDE:**
    * Add or configure an Apache Tomcat 10+ server in your IDE.
    * Set up a Run Configuration for deploying your web application artifact (usually a "war exploded" artifact created
      by the IDE).
    * Ensure the Application context (Context Path) is set correctly (e.g., `/jakarta_labs`).

6. **Build the Project:** Build the project using your IDE or Maven command line (`mvn clean install`).

7. **Deploy and Run:** Deploy the web application artifact onto your configured Tomcat server via the IDE.

8. **Access the Application:** Open a web browser and go to the address where your server is running, followed by the
   context path. For example: `http://localhost:8888/jakarta_labs/` (replace `8888` with your configured port and
   `/jakarta_labs` with your context path).

## Usage

* Upon accessing the application, you will be redirected to the list of apartments.
* You can view detailed information about each apartment by clicking the "View Details" link.
* Click the "Search Apartments by Parameters" link to go to the search form.
* Fill in the search criteria (number of rooms, price range, floor range, boolean parameters) and click "Search".
* The search results page will display apartments that match your criteria (based on the stub implementation).

## Stub Data

The `ApartmentServiceStub` class contains hardcoded dummy data. All data operations (create, read, update, delete,
search) interact with this in-memory list. Any changes made through the forms will persist only while the server is
running; they will be lost when the server restarts.

## Future Development

* Implement the **Business Logic Layer** (Service Layer) to contain business rules and logic, interacting with the Data
  Access Layer.
* Implement the **Data Access Layer** (DAO Layer) to interact with a real database (e.g., using JDBC, JPA, or Hibernate)
  for persistent storage of apartment data.
* Refactor the code to separate concerns more cleanly (e.g., dedicated search criteria class).
* Implement user authentication and authorization to properly distinguish between Owners and Clients.
* Add server-side validation for form inputs.
* Implement more sophisticated search logic.
