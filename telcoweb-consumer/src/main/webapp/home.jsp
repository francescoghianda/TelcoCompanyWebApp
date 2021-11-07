<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:useBean id="username" scope="request" type="java.lang.String"/>
<jsp:useBean id="packages" scope="request" type="java.util.List<it.polimi.telcoejb.entities.ServicePackage>"/>

<c:set var="newLine" value="\n"/>
<html>
<head>
    <title>Home - Telco</title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>

<header class="navbar">


    <c:choose>
        <c:when test="${username.length() != 0}">
            <div class="navbar__item">
                <h4>${username}</h4>
            </div>
        </c:when>
        <c:otherwise>
            <a class="navbar__item" href="<c:url value="/"/>">Sign in</a>
        </c:otherwise>
    </c:choose>

    <c:if test="${username.length() != 0}">
        <a class="navbar__item" href="<c:url value="/logout"/>">Logout</a>
    </c:if>
</header>

<div class="container">

    <h1>Service Packages</h1>



    <div class="sp-grid">

        <c:forEach items="${packages}" var="servicePackage">
            <div class="sp-card">
                <h1 class="sp-card__name">${servicePackage.name}</h1>
                <h2>Included services</h2>
                <ul class="sp-card__services">
                    <c:forEach items="${servicePackage.mobileInternetServices}" var="service">
                        <li>
                            <h3>Mobile internet</h3>
                            <table>
                                <tbody>
                                    <tr><th>Internet:</th><td>${service.gigabytes}GB</td></tr>
                                    <tr><th>Extra fee:</th><td>${service.extraGigabytesFee}&nbsp;€/GB</td></tr>
                                </tbody>
                            </table>
                        </li>
                    </c:forEach>
                    <c:forEach items="${servicePackage.fixedInternetServices}" var="service">
                        <li>
                            <h3>Fixed internet</h3>
                            <table>
                                <tbody>
                                <tr><th>Internet:</th><td>${service.gigabytes}GB</td></tr>
                                <tr><th>Extra fee:</th><td>${service.extraGigabytesFee}&nbsp;€/GB</td></tr>
                                </tbody>
                            </table>
                        </li>
                    </c:forEach>
                    <c:forEach items="${servicePackage.mobilePhoneServices}" var="service">
                        <li>
                            <h3>Mobile phone</h3>
                            <table>
                                <tbody>
                                <tr><th>Minutes:</th><td>${service.minutes}&nbsp;min</td></tr>
                                <tr><th>SMS:</th><td>${service.sms}</td></tr>
                                <tr><th>Extra minutes fee:</th><td>${service.extraMinutesFee}&nbsp;€/min</td></tr>
                                <tr><th>Extra sms fee:</th><td>${service.extraSmsFee}&nbsp;€/SMS</td></tr>
                                </tbody>
                            </table>
                        </li>
                    </c:forEach>
                    <c:forEach items="${servicePackage.fixedPhoneServices}" var="service">
                        <li>
                            <h3>Fixed phone</h3>
                            <p>Unlimited fixed phone calls</p>
                        </li>
                    </c:forEach>
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
