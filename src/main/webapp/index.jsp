<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Apartment Rental</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <%-- Redirect to the apartments list --%>
    <meta http-equiv="refresh" content="0;URL=${pageContext.request.contextPath}/apartments">
</head>
<body>
<p>Loading the apartment list...</p>
<p><a href="${pageContext.request.contextPath}/apartments">Click here if you are not redirected</a></p>
</body>
</html>
