<jsp:useBean id="redirect" scope="request" type="java.lang.String"/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>Telco - Employee Login</title>

  <link rel="stylesheet" href="<c:url value="/css/main.css"/>">

</head>
<body>
<div class="container">

  <jsp:useBean id="error" scope="request" type="java.lang.Integer"/>
  <c:if test="${error == 0}">
    <div class="message-box message-box--error">
        User not found
    </div>
  </c:if>
  <c:if test="${error == 1}">
    <div class="message-box message-box--error">
      Wrong username or password
    </div>
  </c:if>
  <c:if test="${error == 2}">
    <div class="message-box message-box--error">
      Forbidden
    </div>
  </c:if>

  <div class="landing-page-container">
      <h1>Sign in</h1>
      <form class="form" action="<c:url value="/login"/>" method="post">

        <div class="form__input">
          <label for="signin-usr">Username:</label>
          <input type="text" name="username" id="signin-usr">
        </div>

        <div class="form__input">
          <label for="signin-psw">Password:</label>
          <input type="password" name="password" id="signin-psw">
        </div>

        <c:if test="${redirect.length() > 0}">
          <div class="form__input">
            <input type="hidden" name="redirect" value="${redirect}">
          </div>
        </c:if>

        <div class="form__input">
          <button class="primary-button" type="submit">Sign in</button>
        </div>
      </form>

  </div>

</div>

</body>
</html>