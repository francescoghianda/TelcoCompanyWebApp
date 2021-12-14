<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="username" scope="request" type="java.lang.String"/>

<header class="navbar">

    <div class="navbar__item">
        <img class="navbar__logo" src="<c:url value="/img/logo.svg"/>" alt="Logo">
    </div>

    <a class="navbar__item" href="<c:url value="/home"/>">Home</a>

    <div class="navbar__spacer"></div>

    <c:if test="${username.length() != 0}">
        <a class="navbar__item" href="<c:url value="/logout"/>">Logout</a>
    </c:if>

    <c:choose>
        <c:when test="${username.length() != 0}">
            <div class="navbar__item">
                <h4 class="white">${username}</h4>
            </div>
        </c:when>
        <c:otherwise>
            <a class="navbar__item" href="<c:url value="/"/>">Sign in</a>
        </c:otherwise>
    </c:choose>
</header>
