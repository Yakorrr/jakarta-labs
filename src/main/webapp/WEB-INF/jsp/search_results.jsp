<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        .owner-actions {
            white-space: nowrap;
        }
    </style>
</head>
<body>
<h1>Search Results</h1>

<%-- Optional: Display search parameters back --%>
<%-- <c:if test="${searchParameters != null}">
    <p>Search Parameters: Balcony: ${searchParameters.hasBalcony ? 'Yes' : 'No'}, Parking: ${searchParameters.hasParking ? 'Yes' : 'No'}, Furnished: ${searchParameters.isFurnished ? 'Yes' : 'No'}, Minimum Floor: ${searchParameters.floor}</p>
</c:if> --%>


<c:if test="${searchResults != null && not empty searchResults}">
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
        </tr>
        </thead>
        <tbody>
            <%-- Use JSTL forEach to iterate over the search results list --%>
        <c:forEach var="apartment" items="${searchResults}">
            <tr>
                <td><c:out value="${apartment.id}"/></td>
                <td><c:out value="${apartment.address}"/></td>
                <td><c:out value="${apartment.area}"/></td>
                <td><c:out value="${apartment.numberOfRooms}"/></td>
                <td><c:out value="${apartment.price}"/></td>
                <td><c:out value="${apartment.description}"/></td>
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
                        <%-- Link to view apartment details --%>
                    <a href="${pageContext.request.contextPath}/apartment?id=${apartment.id}&mode=view">View Details</a>

                        <%-- Links for owner actions (edit/delete) - still commented out --%>
                        <%-- <c:if test="${isOwner}"> --%>
                    &nbsp; | &nbsp;
                    <a href="${pageContext.request.contextPath}/apartment?id=${apartment.id}&mode=edit">Edit</a>
                    &nbsp; | &nbsp;
                    <form action="${pageContext.request.contextPath}/apartment" method="post" style="display: inline;">
                        <input type="hidden" name="_method" value="DELETE">
                        <input type="hidden" name="id" value="${apartment.id}">
                        <button type="submit"
                                onclick="return confirm('Are you sure you want to delete this apartment?');"
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
</c:if>
<c:if test="${searchResults == null || empty searchResults}">
    <p>No apartments found matching your search criteria.</p>
</c:if>

<p><a href="${pageContext.request.contextPath}/search">Back to Search Form</a></p>
<p><a href="${pageContext.request.contextPath}/apartments">Back to All Apartments</a></p>

</body>
</html>
