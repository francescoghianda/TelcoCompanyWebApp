<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="packages" scope="request" type="java.util.Set<it.polimi.telcoejb.entities.ServicePackage>"/>

<html>
<head>
    <title>Buy a service - Telco</title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>

    <jsp:include page="WEB-INF/fragments/navbar.jsp"/>

    <div class="container">

        <h1>Buy a service package</h1>

        <form method="post" class="form" action="<c:url value="/buy-service"/>">

            <div class="form__input">
                <label for="service-package-chooser">Select a service package:</label>
                <select id="service-package-chooser" name="service-package" required>
                    <c:forEach items="${packages}" var="servicePackage">
                        <option value="${servicePackage.id}">${servicePackage.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form__input">
                <button type="submit" class="primary-button">Select</button>
            </div>
        </form>

    </div>


</body>
</html>
