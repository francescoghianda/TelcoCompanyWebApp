<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="it.polimi.telcoejb.utils.ServiceType" %>

<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<jsp:useBean id="packages" scope="request" type="java.util.Set<it.polimi.telcoejb.entities.ServicePackage>"/>

<c:set var="newLine" value="\n"/>
<html>
<head>
    <title>Home - Telco</title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>

<jsp:include page="WEB-INF/fragments/navbar.jsp"/>

<div class="container">

    <%--@elvariable id="user" type="it.polimi.telcoejb.entities.User"--%>
    <c:if test="${username.length() != 0 && user.insolvent}">
    <jsp:useBean id="rejectedOrders" scope="request" type="java.util.Set<it.polimi.telcoejb.entities.Order>"/>
    <h1>Rejected Orders</h1>
    <table class="rejected-orders-table">
        <thead>
            <tr>
                <th>Order N°</th>
                <th>Service package</th>
                <th>Creation date</th>
                <th>Total price</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${rejectedOrders}" var="order">
            <tr>
                <td class="al-center">${order.id}</td>
                <td>${order.servicePackage.name}</td>
                <td><fmt:formatDate value="${order.creationDate}" pattern="dd-MM-yyyy"/></td>
                <td class="al-right">${order.totalPrice}&nbsp;€</td>
                <td>
                    <a class="primary-button" href="<c:url value="/confirmation?order=${order.id}"/>">Retry</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <hr>
    </c:if>


    <h1>Service Packages</h1>

    <a class="primary-button" href="<c:url value="/buy-service"/>">Buy a service package</a>

    <div class="sp-grid">

        <c:forEach items="${packages}" var="servicePackage">
            <div class="sp-card">
                <h1 class="sp-card__name">${servicePackage.name}</h1>
                <h2>Included services</h2>
                <ul class="sp-card__services">
                    <c:forEach items="${servicePackage.internetServices}" var="service">
                        <li>
                            <h3>${service.serviceType == ServiceType.MOBILE_INTERNET ? "Mobile internet" : "Fixed internet"}</h3>
                            <table>
                                <tbody>
                                    <tr><th>Internet:</th><td>${service.gigabytes}GB</td></tr>
                                    <tr><th>Extra fee:</th><td>${service.extraGigabytesFee}&nbsp;€/GB</td></tr>
                                </tbody>
                            </table>
                        </li>
                    </c:forEach>

                    <%--<c:forEach items="${servicePackage.fixedInternetServices}" var="service">
                        <li>
                            <h3>Fixed internet</h3>
                            <table>
                                <tbody>
                                <tr><th>Internet:</th><td>${service.gigabytes}GB</td></tr>
                                <tr><th>Extra fee:</th><td>${service.extraGigabytesFee}&nbsp;€/GB</td></tr>
                                </tbody>
                            </table>
                        </li>
                    </c:forEach>--%>

                    <c:forEach items="${servicePackage.phoneServices}" var="service">
                        <li>
                            <c:choose>
                                <c:when test="${service.serviceType == ServiceType.MOBILE_PHONE}">
                                    <h3>Mobile phone</h3>
                                    <table>
                                        <tbody>
                                        <tr><th>Minutes:</th><td>${service.minutes}&nbsp;min</td></tr>
                                        <tr><th>SMS:</th><td>${service.sms}</td></tr>
                                        <tr><th>Extra minutes fee:</th><td>${service.extraMinutesFee}&nbsp;€/min</td></tr>
                                        <tr><th>Extra sms fee:</th><td>${service.extraSmsFee}&nbsp;€/SMS</td></tr>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <h3>Fixed phone</h3>
                                    <p>Unlimited fixed phone calls</p>
                                </c:otherwise>
                            </c:choose>

                        </li>
                    </c:forEach>

                    <%--
                        <c:forEach items="${servicePackage.fixedPhoneServices}" var="service">
                        <li>
                            <h3>Fixed phone</h3>
                            <p>Unlimited fixed phone calls</p>
                        </li>
                    </c:forEach>
                    --%>
                </ul>
                <h2>Optional products</h2>
                <ul class="sp-card__op">
                    <c:forEach items="${servicePackage.optionalProducts}" var="op">
                        <li>${op.name}&nbsp;(${op.monthlyFee}&nbsp;€/month)</li>
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>

    </div>



</div>

</body>
</html>
