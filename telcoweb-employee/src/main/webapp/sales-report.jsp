<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="valueOfSalesPerPackage" scope="request" type="java.util.List<it.polimi.telcoejb.entities.statistics.TotalValueOfSalesPerPackage>"/>
<jsp:useBean id="purchasesPerPackage" scope="request" type="java.util.List<it.polimi.telcoejb.entities.statistics.PurchasesPerPackage>"/>
<jsp:useBean id="purchasesPerPackageAndValidityPeriod" scope="request" type="java.util.List<it.polimi.telcoejb.entities.statistics.PurchasesPerPackageAndValidityPeriod>"/>
<jsp:useBean id="avgOptionalProductsPerPackage" scope="request" type="java.util.List<it.polimi.telcoejb.entities.statistics.AverageOptionalProductPerPackage>"/>
<jsp:useBean id="bestSellerOptionalProduct" scope="request" type="it.polimi.telcoejb.entities.statistics.TotalValueOfSalesPerOptionalProduct"/>
<jsp:useBean id="suspendedOrdersAndAlerts" scope="request" type="java.util.List<it.polimi.telcowebemployee.utils.UserSuspendedOrdersAndAlerts>"/>


<html>
<head>
    <title>Sales Report - Telco</title>

    <link rel="stylesheet" href="<c:url value="/css/main.css"/>">

</head>
<body>
    <jsp:include page="WEB-INF/fragments/navbar.jsp"/>

    <div class="container">
        <h1>Sales Report</h1>
        <div class="sales-container">
            <div>
                <h3>Total value of sales per package:</h3>
                <table class="table table--vertical">
                    <thead>
                    <tr>
                        <th class="separator">Service package</th>
                        <th>Tot. value of sales</th>
                        <th>Tot. value of sales without optional products</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${valueOfSalesPerPackage}" var="stat">
                        <tr>
                            <td class="separator">${stat.servicePackage.name}</td>
                            <td>${stat.totalValueOfSales}€</td>
                            <td>${stat.totalValueOfSalesWithoutOptionalProducts}€</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div>
                <h3>Number of sales per package:</h3>
                <table class="table table--vertical">
                    <thead>
                    <tr>
                        <th class="separator">Service package</th>
                        <th>Number of sales</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${purchasesPerPackage}" var="stat">
                        <tr>
                            <td class="separator">${stat.servicePackage.name}</td>
                            <td>${stat.totalPurchases}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div>
                <h3>Number of sales per package and validity period:</h3>
                <table class="table table--vertical">
                    <thead>
                    <tr>
                        <th>Service package</th>
                        <th class="separator">Validity period</th>
                        <th>Number of sales</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${purchasesPerPackageAndValidityPeriod}" var="stat" varStatus="loop">
                        <tr class="${loop.index < purchasesPerPackageAndValidityPeriod.size()-1 ? (purchasesPerPackageAndValidityPeriod.get(loop.index+1).servicePackage.name.equals(stat.servicePackage.name) ? '' : 'h-separator') : ''}">
                            <td>${stat.servicePackage.name}</td>
                            <td class="separator">${stat.validityPeriod.months} months</td>
                            <td>${stat.totalPurchases}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div>
                <h3>Average number of optional products sold with each service package:</h3>
                <table class="table table--vertical">
                    <thead>
                    <tr>
                        <th class="separator">Service package</th>
                        <th>Average number of sales</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${avgOptionalProductsPerPackage}" var="stat">
                        <tr>
                            <td class="separator">${stat.servicePackage.name}</td>
                            <td>${stat.averageOptionalProductSalesPerPackage}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div>
                <h3>Best seller optional product:</h3>
                <table class="table table--horizontal">
                    <tbody>
                    <tr>
                        <th class="separator hz">Name</th>
                        <td>${bestSellerOptionalProduct.optionalProduct.name}</td>
                    </tr>
                    <tr>
                        <th class="separator hz">Monthly fee</th>
                        <td>${bestSellerOptionalProduct.optionalProduct.monthlyFee} €/month</td>
                    </tr>
                    <tr>
                        <th class="separator hz">Total value of sales</th>
                        <td>${bestSellerOptionalProduct.totalValueOfSales} €</td>
                    </tr>
                    </tbody>
                </table>

            </div>

            <div style="grid-column: span 2">
                <h3>Insolvent users, suspended orders and alerts</h3>
                <table class="table table--vertical">
                    <thead>
                    <tr>
                        <th rowspan="2">User Id</th>
                        <th class="separator" rowspan="2">Username</th>
                        <th colspan="3" class="separator">Suspended order</th>
                        <th colspan="2">Payment Alert</th>
                    </tr>
                    <tr class="sub-header-row">
                        <th>Order Id</th>
                        <th>Service package</th>
                        <th class="separator">Amount</th>
                        <th>Creation date</th>
                        <th>Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${suspendedOrdersAndAlerts}" var="var">
                            <tr>
                                <td>${var.user.id}</td>
                                <td class="separator">${var.user.username}</td>
                                <td class="inner-table-container separator" colspan="3">
                                    <table class="inner-table">
                                        <c:forEach items="${var.suspendedOrders}" var="order">
                                            <tr>
                                                <td>${order.id}</td>
                                                <td>${order.servicePackage.name}</td>
                                                <td>${order.totalPrice}&nbsp;€</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                                <td class="inner-table-container" colspan="2">
                                    <table class="inner-table">
                                        <c:forEach items="${var.alerts}" var="alert">
                                            <tr>
                                                <td>${alert.creationTime}</td>
                                                <td>${alert.amount}&nbsp;€</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>

    </div>

</body>
</html>
