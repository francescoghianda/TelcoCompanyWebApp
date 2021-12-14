<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="servicePackage" scope="request" type="it.polimi.telcoejb.entities.ServicePackage"/>

<html>
<head>
    <title>Buy a service - Telco</title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>

    <jsp:include page="WEB-INF/fragments/navbar.jsp"/>

    <div class="container">

        <h1>Choose the options available for this service package</h1>
        <h3>Selected service package: ${servicePackage.name}</h3>

        <form method="post" action="<c:url value="/create-subscription"/>">

            <input type="hidden" name="service-package" value="${servicePackage.id}">

            <div class="form__input">
                <label for="validity-period-chooser"><strong>Select a validity period:</strong></label>
                <select id="validity-period-chooser" name="validity-period" required>
                    <c:forEach items="${servicePackage.validityPeriods}" var="validityPeriod">
                        <option value="${validityPeriod.id}">${validityPeriod.months}&nbsp;months&nbsp;(${validityPeriod.monthlyFee}&nbsp;€/month)</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form__input">
                <fieldset>
                    <legend><strong>(Optional) Select one or more optional products:</strong></legend>
                    <c:forEach items="${servicePackage.optionalProducts}" var="optionalProduct">
                        <div class="checkbox">
                            <input type="checkbox" value="${optionalProduct.id}" name="opt-product" id="opt-product-${optionalProduct.id}">
                            <label for="opt-product-${optionalProduct.id}">${optionalProduct.name}&nbsp;(${optionalProduct.monthlyFee}&nbsp;€/month)</label>
                        </div>
                    </c:forEach>
                </fieldset>
            </div>

            <div class="form__input">
                <label for="start-date-chooser"><strong>Select a start date for the subscription:</strong></label>
                <input id="start-date-chooser" type="date" name="start-date" required>
            </div>

            <div class="form__input">
                <button class="primary-button" type="submit">Confirm</button>
            </div>
        </form>
    </div>

</body>
</html>
