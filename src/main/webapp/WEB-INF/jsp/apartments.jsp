<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Apartments List</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
<h1>Apartments for Rent</h1>

<%-- Link to the search form --%>
<p><a href="${pageContext.request.contextPath}/search">Search Apartments by Parameters</a></p>

<%-- You might want to add a link to create a new apartment for owners --%>
<%-- Assuming you have a way to identify if the user is an owner (e.g., session attribute) --%>
<%-- <c:if test="${isOwner}"> --%>
<p><a href="${pageContext.request.contextPath}/apartment?mode=create">Add New Apartment</a></p>
<%-- </c:if> --%>


<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Address</th>
        <th>Area (sq m)</th>
        <th>Rooms</th>
        <th>Price ($)</th>
        <th>Description</th>
        <th>Balcony</th>
        <th>Parking</th>
        <th>Furnished</th>
        <th>Floor</th>
        <th>Actions</th>
        <%-- Column for actions --%>
    </tr>
    </thead>
    <tbody>
    <%-- Use JSTL forEach to iterate over the list of apartments --%>
    <c:forEach var="apartment" items="${listApartments}">
        <tr>
            <td><c:out value="${apartment.id}"/></td>
            <td><c:out value="${apartment.address}"/></td>
            <td><c:out value="${apartment.area}"/></td>
            <td><c:out value="${apartment.numberOfRooms}"/></td>
            <td><c:out value="${apartment.price}"/></td>
            <td><c:out value="${apartment.description}"/></td>
                <%-- Using JSTL if for conditional formatting --%>
            <td>
                <c:if test="${apartment.parameters.hasBalcony}">
                    Yes
                </c:if>
                <c:if test="${!apartment.parameters.hasBalcony}">
                    No
                </c:if>
            </td>
            <td>
                <c:if test="${apartment.parameters.hasParking}">
                    Yes
                </c:if>
                <c:if test="${!apartment.parameters.hasParking}">
                    No
                </c:if>
            </td>
                <%-- Using JSTL choose/when for selection --%>
            <td>
                <c:choose>
                    <c:when test="${apartment.parameters.hasFurniture}">
                        Yes
                    </c:when>
                    <c:otherwise>
                        No
                    </c:otherwise>
                </c:choose>
            </td>
            <td><c:out value="${apartment.parameters.floor}"/></td>
            <td class="owner-actions">
                    <%-- Link to view apartment details for clients --%>
                <a href="${pageContext.request.contextPath}/apartment?id=${apartment.id}&mode=view">View Details</a>

                    <%-- Links for owner actions (edit/delete) --%>
                    <%-- Assuming you have a way to identify if the user is an owner --%>
                    <%-- <c:if test="${isOwner}"> --%>
                &nbsp; | &nbsp;
                <a href="${pageContext.request.contextPath}/apartment?id=${apartment.id}&mode=edit">Edit</a>
                &nbsp; | &nbsp;
                    <%-- Form for DELETE request (DELETE method in HTML forms is not directly supported,
                         often simulated via POST with a hidden field or JavaScript) --%>
                <form action="${pageContext.request.contextPath}/apartment" method="post" style="display: inline;">
                    <input type="hidden" name="_method" value="DELETE"> <%-- Simulate DELETE --%>
                    <input type="hidden" name="id" value="${apartment.id}">
                    <button type="submit" onclick="return confirm('Are you sure you want to delete this apartment?');"
                            style="background: none; border: none; padding: 0; color: blue; text-decoration: underline; cursor: pointer;">
                        Delete
                    </button>
                </form>
                    <%-- </c:if> --%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
