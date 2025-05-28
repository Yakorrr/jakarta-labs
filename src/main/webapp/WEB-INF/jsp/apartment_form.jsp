<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${apartment.id != 0}">Edit Apartment</c:when>
            <c:otherwise>Add New Apartment</c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>
    <c:choose>
        <c:when test="${apartment.id != 0}">Edit Apartment</c:when>
        <c:otherwise>Add New Apartment</c:otherwise>
    </c:choose>
</h1>

<%-- The form submits to the ApartmentServlet --%>
<form action="${pageContext.request.contextPath}/apartment" method="post">

    <%-- Include ID for update operations --%>
    <c:if test="${apartment.id != 0}">
        <input type="hidden" name="id" value="${apartment.id}">
        <input type="hidden" name="action" value="update"> <%-- Indicate update action --%>
    </c:if>
    <c:if test="${apartment.id == 0}">
        <input type="hidden" name="action" value="create"> <%-- Indicate create action --%>
    </c:if>

    <div class="form-group">
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="<c:out value="${apartment.address}"/>" required>
    </div>

    <div class="form-group">
        <label for="area">Area (sq m):</label>
        <input type="number" id="area" name="area" value="<c:out value="${apartment.area}"/>" step="0.1" required>
    </div>

    <div class="form-group">
        <label for="numberOfRooms">Number of Rooms:</label>
        <input type="number" id="numberOfRooms" name="numberOfRooms" value="<c:out value="${apartment.numberOfRooms}"/>"
               min="1" required>
    </div>

    <div class="form-group">
        <label for="floor">Floor:</label>
        <input type="number" id="floor" name="floor"
               value="<c:out value="${apartment.parameters != null ? apartment.parameters.floor : ''}"/>" min="0"
               required>
    </div>

    <div class="form-group">
        <label for="price">Price ($):</label>
        <input type="number" id="price" name="price" value="<c:out value="${apartment.price}"/>" step="0.01" required>
    </div>

    <div class="form-group">
        <label for="description">Description:</label>
        <textarea id="description" name="description" required><c:out value="${apartment.description}"/></textarea>
    </div>

    <h2>Parameters:</h2>

    <div class="form-group">
        <label for="hasBalcony">
            <input type="checkbox" id="hasBalcony" name="hasBalcony"
                   <c:if test="${apartment.parameters != null && apartment.parameters.hasBalcony}">checked</c:if>>
            Balcony
        </label>
    </div>

    <div class="form-group">
        <label for="hasParking">
            <input type="checkbox" id="hasParking" name="hasParking"
                   <c:if test="${apartment.parameters != null && apartment.parameters.hasParking}">checked</c:if>>
            Parking
        </label>
    </div>

    <div class="form-group">
        <label for="isFurnished">
            <input type="checkbox" id="isFurnished" name="isFurnished"
                   <c:if test="${apartment.parameters != null && apartment.parameters.hasFurniture}">checked</c:if>>
            Furnished
        </label>
    </div>

    <div class="button-group">
        <button type="submit">Save Apartment</button>
        <a href="${pageContext.request.contextPath}/apartments">Cancel</a>
    </div>

</form>

</body>
</html>
