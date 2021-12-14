<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="it.polimi.telcoejb.utils.ServiceType" %>

<jsp:useBean id="order" scope="request" type="it.polimi.telcoejb.entities.Order"/>
<jsp:useBean id="username" scope="request" type="java.lang.String"/>

<html>
<head>
    <title>Confirmation - Telco</title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">

</head>
<body>
    <jsp:include page="WEB-INF/fragments/navbar.jsp"/>

    <div class="container">
        <h1>Order summary</h1>

        <div class="summary">
            <hr>
            <table>
                <thead>
                    <tr>
                        <th>Order Date</th>
                        <th>Activation Date</th>
                        <th>Duration (months)</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><fmt:formatDate value="${order.creationDate}" pattern="dd-MM-yyyy"/></td>
                        <td><fmt:formatDate value="${order.startDate}" pattern="dd-MM-yyyy"/></td>
                        <td>${order.validityPeriod.months}</td>
                    </tr>
                </tbody>
            </table>
            <hr>
            <div class="summary__row">
                <div class="summary__left">
                    <h3>${order.servicePackage.name} service package&nbsp;(${order.validityPeriod.monthlyFee}&nbsp;€/month)</h3>
                    <ul>
                        <%--
                        <c:forEach items="${order.servicePackage.fixedPhoneServices}" var="service">
                            <li><h4 class="grey">Unlimited fixed phone calls</h4></li>
                        </c:forEach>
                        --%>
                        <c:forEach items="${order.servicePackage.phoneServices}" var="service">
                            <li>
                                <c:choose>
                                    <c:when test="${service.serviceType == ServiceType.MOBILE_PHONE}">
                                        <h4 class="grey">${service.minutes} min. of mobile phone calls + ${service.sms} sms</h4>
                                    </c:when>
                                    <c:otherwise>
                                        <h4 class="grey">Unlimited fixed phone calls</h4>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:forEach>
                        <%--
                        <c:forEach items="${order.servicePackage.fixedInternetServices}" var="service">
                            <li><h4 class="grey">${service.gigabytes} GB of fixed internet data</h4></li>
                        </c:forEach>
                        --%>
                        <c:forEach items="${order.servicePackage.internetServices}" var="service">
                            <li>
                                <c:choose>
                                    <c:when test="${service.serviceType == ServiceType.MOBILE_INTERNET}">
                                        <h4 class="grey">${service.gigabytes} GB of mobile data</h4>
                                    </c:when>
                                    <c:otherwise>
                                        <h4 class="grey">${service.gigabytes} GB of fixed internet data</h4>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="summary__right">
                    <h3>€&nbsp;${order.validityPeriod.months*order.validityPeriod.monthlyFee}</h3>
                </div>
            </div>
            <hr>
            <h2>Optional Products</h2>
            <c:choose>
                <c:when test="${order.optionalProducts.size() > 0}">
                    <c:forEach items="${order.optionalProducts}" var="optProduct">
                        <div class="summary__row">
                            <div class="summary__left">
                                <h3>${optProduct.name}&nbsp;(${optProduct.monthlyFee}&nbsp;€/month)</h3>
                            </div>
                            <div class="summary__right">
                                <h3>€&nbsp;${order.validityPeriod.months*optProduct.monthlyFee}</h3>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="summary__row">
                        <div class="summary__left">
                            <h3>No optional product selected</h3>
                        </div>
                        <div class="summary__right">
                            <h3>€&nbsp;0</h3>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>

            <hr>
            <div class="summary__row">
                <div class="summary__left">
                    <h2>Total</h2>
                </div>
                <div class="summary__right">
                    <h2>€&nbsp;${order.totalPrice}</h2>
                </div>
            </div>
            <hr>
        </div>

        <c:choose>
            <c:when test="${username.length() > 0}">
                <form action="<c:url value="/confirmation"/>" method="post">
                    <c:if test="${order.owner != null}">
                        <input type="hidden" name="order" value="${order.id}">
                    </c:if>
                    <div class="form__input">
                        <button type="submit" class="primary-button">Buy</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <a class="primary-button" href="<c:url value="/?onlylogin&redirect=/confirmation?order=${order.id}"/>">Sign in to continue</a>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
