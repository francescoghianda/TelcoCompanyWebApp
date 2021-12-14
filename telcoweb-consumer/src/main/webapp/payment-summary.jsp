<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="success" scope="request" type="java.lang.Boolean"/>
<jsp:useBean id="username" scope="request" type="java.lang.String"/>

<html>
<head>
    <title>${success ? "Success" : "Failure"} - Telco</title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">
</head>
<body>

    <jsp:include page="WEB-INF/fragments/navbar.jsp"/>

  <div class="container">
      <h1>${success ? "Order Confirmed!" : "Payment rejected"}</h1>
      <c:choose>
          <c:when test="${success}">
              <p>Hi ${username},
                  Your order has been confirmed.
              </p>
          </c:when>
          <c:otherwise>
              <p>There was a problem during the payment process</p>
          </c:otherwise>
      </c:choose>
  </div>

</body>
</html>
