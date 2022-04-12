<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="username" scope="request" type="java.lang.String"/>

<header class="navbar">

    <div class="navbar__item">
        <img class="navbar__logo" src="<c:url value="/img/logo.svg"/>" alt="Logo">
    </div>

    <h3 class="navbar__item white" style="user-select: none;">Employee Dashboard</h3>

    <a class="navbar__item white" href="<c:url value="/"/>">Home</a>
    <a class="navbar__item white" href="<c:url value="/sales-report"/>">Sales Report</a>

    <div class="navbar__spacer"></div>

    <a class="navbar__item" href="<c:url value="/logout"/>">Logout</a>

    <div class="navbar__item">
        <h4 class="white">${username}</h4>
    </div>

</header>
