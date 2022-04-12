<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="services" scope="request" type="java.util.List<it.polimi.telcoejb.entities.Service>"/>
<jsp:useBean id="optionalProducts" scope="request" type="java.util.List<it.polimi.telcoejb.entities.OptionalProduct>"/>
<jsp:useBean id="validityPeriods" scope="request" type="java.util.List<it.polimi.telcoejb.entities.ValidityPeriod>"/>

<html>
<head>
    <title>Telco - Employee Dashboard</title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">

</head>
<body>

    <jsp:include page="WEB-INF/fragments/navbar.jsp"/>

    <div class="container">

        <div>
            <form class="form" method="post" action="<c:url value="/new-service"/>">
                <h1 class="form__title">Create a service</h1>
                <fieldset>
                    <legend><strong>Service type</strong></legend>
                    <div class="form__input form__input--radio">
                        <input type="radio" name="service_type" value="MOBILE_INTERNET" id="st_mob_int" required>
                        <label for="st_mob_int">MOBILE_INTERNET</label>
                    </div>
                    <div class="form__input form__input--radio">
                        <input type="radio" name="service_type" value="FIXED_INTERNET" id="st_fix_int" required>
                        <label for="st_fix_int">FIXED_INTERNET</label>
                    </div>
                    <div class="form__input form__input--radio">
                        <input type="radio" name="service_type" value="MOBILE_PHONE" id="st_mob_pho" required>
                        <label for="st_mob_pho">MOBILE_PHONE</label>
                    </div>
                </fieldset>

                <fieldset class="no-border" id="phone_service_fieldset" style="display: none" disabled>
                    <div class="form__input">
                        <label for="min_input">Minutes</label>
                        <input name="minutes" id="min_input" type="number" min="0" step="1" required>
                    </div>
                    <div class="form__input">
                        <label for="sms_input">SMS</label>
                        <input name="sms" id="sms_input" type="number" min="0" step="1" required>
                    </div>
                    <div class="form__input">
                        <label for="min_fee_input">Extra minutes fee</label>
                        <input name="extra_minutes_fee" id="min_fee_input" type="number" min="0" step="0.01" required>
                    </div>
                    <div class="form__input">
                        <label for="sms_fee_input">Extra SMS fee</label>
                        <input name="extra_sms_fee" id="sms_fee_input" type="number" min="0" step="0.01" required>
                    </div>
                </fieldset>

                <fieldset class="no-border" id="internet_service_fieldset" style="display: none" disabled>
                    <div class="form__input">
                        <label for="gb_input">Gigabytes</label>
                        <input name="gigabytes" id="gb_input" type="number" min="0" step="1" required>
                    </div>
                    <div class="form__input">
                        <label for="gb_fee_input">Extra GB fee</label>
                        <input name="extra_gigabytes_fee" id="gb_fee_input" type="number" min="0" step="0.01" required>
                    </div>
                </fieldset>

                <div class="form__input">
                    <button id="create-service-btn" class="primary-button" type="submit" disabled>Create Service</button>
                </div>
            </form>
        </div>

        <div>
            <form class="form" method="post" action="<c:url value="/new-opt-product"/>">
                <h1 class="form__title">Create optional product</h1>
                <div class="form__input">
                    <label for="opt-product-name-input">Name</label>
                    <input type="text" name="name" id="opt-product-name-input" required>
                </div>
                <div class="form__input">
                    <label for="opt-product-monthly-fee-input">Monthly fee</label>
                    <input type="number" name="monthly_fee" id="opt-product-monthly-fee-input" min="0" step="0.01" required>
                </div>
                <div class="form__input">
                    <button class="primary-button" type="submit">Create optional product</button>
                </div>
            </form>
        </div>

        <div>
            <form class="form" method="post" action="<c:url value="/new-service-package"/>">
                <h1 class="form__title">Create service package</h1>
                <div class="form__input">
                    <label for="service-package-name-input">Name</label>
                    <input type="text" name="name" id="service-package-name-input" required>
                </div>
                <div class="form__input">
                    <fieldset>
                        <legend><strong>Services</strong></legend>
                        <c:forEach items="${services}" var="service">
                            <div class="checkbox">
                                <input type="checkbox" id="service-${service.id}" value="${service.id}" name="services">
                                <label for="service-${service.id}">
                                    <c:choose>
                                        <c:when test="${service.serviceType == 'FIXED_PHONE'}">
                                            Fixed phone service
                                        </c:when>
                                        <c:when test="${service.serviceType == 'MOBILE_PHONE'}">
                                            Mobile phone service (Minutes: ${service.minutes}, SMS: ${service.sms}, Extra minutes fee: ${service.extraMinutesFee} €/min , Extra SMS fee: ${service.extraSmsFee} €/SMS)
                                        </c:when>
                                        <c:when test="${service.serviceType == 'FIXED_INTERNET'}">
                                            Fixed internet service (Gigabytes: ${service.gigabytes}, Extra gigabytes fee: ${service.extraGigabytesFee} €/GB)
                                        </c:when>
                                        <c:when test="${service.serviceType == 'MOBILE_INTERNET'}">
                                            Mobile internet service (Gigabytes: ${service.gigabytes}, Extra gigabytes fee: ${service.extraGigabytesFee} €/GB)
                                        </c:when>
                                    </c:choose>
                                </label>
                            </div>
                        </c:forEach>
                    </fieldset>

                    <fieldset>
                        <legend><strong>Optional products</strong></legend>
                        <c:forEach items="${optionalProducts}" var="product">
                            <div class="checkbox">
                                <input type="checkbox" id="product-${product.id}" value="${product.id}" name="optional-products">
                                <label for="product-${product.id}">${product.name} - ${product.monthlyFee} €/month</label>
                            </div>
                        </c:forEach>
                    </fieldset>

                    <fieldset>
                        <legend><strong>Validity periods</strong></legend>
                        <c:forEach items="${validityPeriods}" var="period">
                            <div class="checkbox">
                                <input type="checkbox" id="period-${period.id}" value="${period.id}" name="validity-periods">
                                <label for="period-${period.id}">${period.months} months - ${period.monthlyFee} €/month</label>
                            </div>
                        </c:forEach>
                    </fieldset>

                    <div class="form__input">
                        <button class="primary-button" type="submit">Create service package</button>
                    </div>
                </div>

            </form>
        </div>

    </div>


    <script>
        const createServiceBtn = document.getElementById('create-service-btn');
        const internetServiceFieldset =  document.getElementById('internet_service_fieldset');
        const phoneServiceFieldset =  document.getElementById('phone_service_fieldset');
        const serviceTypeRadioBtn = document.querySelectorAll('input[name="service_type"]');

        for(let btn of serviceTypeRadioBtn){
            btn.addEventListener('change', onServiceTypeChange);
        }

        function onServiceTypeChange(){
            createServiceBtn.disabled = false;
            let selectedValue = document.querySelector('input[name="service_type"]:checked').value;
            if(selectedValue === "MOBILE_INTERNET" || selectedValue === "FIXED_INTERNET"){
               internetServiceFieldset.disabled = false;
               internetServiceFieldset.style.display = 'block';
               phoneServiceFieldset.disabled = true;
               phoneServiceFieldset.style.display = 'none';
            }
            else if(selectedValue === "MOBILE_PHONE"){
                internetServiceFieldset.disabled = true;
                internetServiceFieldset.style.display = 'none';
                phoneServiceFieldset.disabled = false;
                phoneServiceFieldset.style.display = 'block';
            }
        }
    </script>

</body>
</html>
