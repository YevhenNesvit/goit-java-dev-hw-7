<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Invalid customer id format</title>
        <style>
           .center {
            text-align: center;
            margin-top: 11%;
           }
          </style>
    </head>
    <body>
        <c:import url="${contextPath}/WEB-INF/view/navigationBar.jsp"/>
        <div class="center">
          <h2><b>Invalid value. Please, use digits for customer_id</b></h2>
          <p><a href="/createCustomerForm">Create customer</a></p>
          <p><a href="/deleteCustomerForm">Delete customer</a></p>
          <p><a href="/getCustomerByIdForm">Get customer by id</a></p>
          <p><a href="/updateCustomerForm">Update customer</a></p>
        </div>
    </body>
</html>