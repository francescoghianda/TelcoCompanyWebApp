<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Telco</title>

  <link rel="stylesheet" href="<c:url value="/css/main.css"/>">

</head>
<body>
<div class="landing-page-container">

  <div>
    <h1>Sign in</h1>
    <form class="form" action="<c:url value="/signin"/>" method="post">

      <div class="form__input">
        <label for="signin-usr">Username:</label>
        <input type="text" name="username" id="signin-usr">
      </div>

      <div class="form__input">
        <label for="signin-psw">Password:</label>
        <input type="password" name="password" id="signin-psw">
      </div>

      <div class="form__input">
        <button class="primary-button" type="submit">Sign in</button>
      </div>

    </form>
  </div>

  <div>
    <h1>Sign up</h1>
    <form class="form" action="<c:url value="/signup"/>" method="post">

      <div class="form__input">
        <label for="signup-email">Email:</label>
        <input type="email" name="email" id="signup-email">
      </div>

      <div class="form__input">
        <label for="signup-usr">Username:</label>
        <input type="text" name="username" id="signup-usr">
      </div>

      <div class="form__input">
        <label for="signup-psw">Password:</label>
        <input type="password" name="password" id="signup-psw">
      </div>

      <div class="form__input">
        <button class="primary-button" type="submit">Sign up</button>
      </div>

    </form>
  </div>

  <div class="bottom-space">
    <h1>or</h1>
    <a class="primary-button" href="<c:url value="/home"/>">Continue without login</a>
  </div>


</div>

</body>
</html>