<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home - Telco</title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>

<header class="navbar">
    <jsp:useBean id="username" scope="request" type="java.lang.String"/>

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

    <jsp:useBean id="packages" scope="request" type="java.util.List<it.polimi.telcoejb.entities.ServicePackage>"/>
    <table>
        <thead>
            <tr>
                <td>Name</td>
                <td>Services</td>
                <td>Optional Products</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${packages}" var="servicePackage">
                <tr>
                    <td>${servicePackage.name}</td>
                    <td>

                    </td>
                    <td>
                        <c:forEach items="${servicePackage.optionalProducts}" var="product">
                            ${product.name},
                        </c:forEach>
                    </td>
                </tr>

            </c:forEach>
        </tbody>

    </table>



</div>

</body>
</html>
