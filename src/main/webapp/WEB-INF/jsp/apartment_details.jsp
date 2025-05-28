<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Apartment Details: <c:out value="${apartment.address}"/></title>
    <style>
        .detail-item {
            margin-bottom: 10px;
        }

        .detail-label {
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Apartment Details</h1>

<c:if test="${apartment != null}">
    <div class="detail-item">
        <span class="detail-label">ID:</span> <c:out value="${apartment.id}"/>
    </div>
    <div class="detail-item">
        <span class="detail-label">Address:</span> <c:out value="${apartment.address}"/>
    </div>
    <div class="detail-item">
        <span class="detail-label">Area:</span> <c:out value="${apartment.area}"/> sq m
    </div>
    <div class="detail-item">
        <span class="detail-label">Number of Rooms:</span> <c:out value="${apartment.numberOfRooms}"/>
    </div>
    <div class="detail-item">
        <span class="detail-label">Price:</span> <c:out value="${apartment.price}"/> $
    </div>
    <div class="detail-item">
        <span class="detail-label">Description:</span> <c:out value="${apartment.description}"/>
    </div>

    <h2>Parameters:</h2>
    <c:if test="${apartment.parameters != null}">
        <div class="detail-item">
            <span class="detail-label">Balcony:</span>
                <%-- Using JSTL if for conditional display based on boolean --%>
            <c:if test="${apartment.parameters.hasBalcony}">
                Yes
            </c:if>
            <c:if test="${!apartment.parameters.hasBalcony}">
                No
            </c:if>
        </div>
        <div class="detail-item">
            <span class="detail-label">Parking:</span>
                <%-- Using JSTL if for conditional display --%>
            <c:if test="${apartment.parameters.hasParking}">
                Yes
            </c:if>
            <c:if test="${!apartment.parameters.hasParking}">
                No
            </c:if>
        </div>
        <div class="detail-item">
            <span class="detail-label">Furnished:</span>
                <%-- Using JSTL choose/when for selection --%>
            <c:choose>
                <c:when test="${apartment.parameters.hasFurniture}">
                    Yes
                </c:when>
                <c:otherwise>
                    No
                </c:otherwise>
            </c:choose>
        </div>
        <div class="detail-item">
            <span class="detail-label">Floor:</span> <c:out value="${apartment.parameters.floor}"/>
        </div>
    </c:if>
    <c:if test="${apartment.parameters == null}">
        <p>No parameters are available for this apartment.</p>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/apartments">Back to Apartments List</a></p>

    <%-- Links for owner actions (edit/delete) - still commented out --%>
    <%-- Assuming you have a way to identify if the user is an owner --%>
    <%-- <c:if test="${isOwner}"> --%>
    <p>
        <a href="${pageContext.request.contextPath}/apartment?id=${apartment.id}&mode=edit">Edit this Apartment</a>
        &nbsp; | &nbsp;
    <form action="${pageContext.request.contextPath}/apartment" method="post" style="display: inline;">
        <input type="hidden" name="_method" value="DELETE">
        <input type="hidden" name="id" value="${apartment.id}">
        <button type="submit" onclick="return confirm('Are you sure you want to delete this apartment?');"
                style="background: none; border: none; padding: 0; color: blue; text-decoration: underline; cursor: pointer;">
            Delete this Apartment
        </button>
    </form>
    </p>
    <%-- </c:if> --%>

</c:if>
<c:if test="${apartment == null}">
    <p>Apartment not found.</p>
    <p><a href="${pageContext.request.contextPath}/apartments">Back to Apartments List</a></p>
</c:if>

</body>
</html>
