<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Apartments</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>Search Apartments</h1>

<%-- The form submits to the SearchServlet --%>
<form action="${pageContext.request.contextPath}/search" method="post">

    <h2>Parameters for Search:</h2>

    <div class="form-group">
        <label for="searchNumberOfRooms">Number of Rooms:</label>
        <input type="number" id="searchNumberOfRooms" name="searchNumberOfRooms"
               value="<c:out value="${searchParameters.searchNumberOfRooms}"/>" min="0">
    </div>

    <div class="form-group">
        <label>Price Range ($):</label>
        <div class="price-range">
            <label for="searchMinPrice">Min:</label>
            <input type="number" id="searchMinPrice" name="searchMinPrice"
                   value="<c:out value="${searchParameters.searchMinPrice}"/>"
                   step="0.01" min="0">
            <label for="searchMaxPrice">Max:</label>
            <input type="number" id="searchMaxPrice" name="searchMaxPrice"
                   value="<c:out value="${searchParameters.searchMaxPrice}"/>"
                   step="0.01" min="0">
        </div>
    </div>

    <div class="form-group">
        <label>Floor Range:</label>
        <div class="floor-range">
            <label for="searchMinFloor">Min:</label>
            <input type="number" id="searchMinFloor" name="searchMinFloor"
                   value="<c:out value="${searchParameters.searchMinFloor}"/>" min="0">
            <label for="searchMaxFloor">Max:</label>
            <input type="number" id="searchMaxFloor" name="searchMaxFloor"
                   value="<c:out value="${searchParameters.searchMaxFloor}"/>" min="0">
        </div>
    </div>


    <div class="form-group">
        <label for="hasBalcony">
            <input type="checkbox" id="hasBalcony" name="hasBalcony"
                   <c:if test="${searchParameters.hasBalcony}">checked</c:if>> Has Balcony
        </label>
    </div>

    <div class="form-group">
        <label for="hasParking">
            <input type="checkbox" id="hasParking" name="hasParking"
                   <c:if test="${searchParameters.hasParking}">checked</c:if>> Has Parking
        </label>
    </div>

    <div class="form-group">
        <label for="isFurnished">
            <input type="checkbox" id="isFurnished" name="isFurnished"
                   <c:if test="${searchParameters.hasFurniture}">checked</c:if>> Is Furnished
        </label>
    </div>

    <div class="button-group">
        <button type="submit">Search</button>
        <a href="${pageContext.request.contextPath}/apartments">Cancel</a>
    </div>

</form>

</body>
</html>
