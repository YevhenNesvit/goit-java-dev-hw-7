<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Get developers by skill name</title>
        <style>
            .center {
                text-align: center;
                margin-top: 11%;
            }
            .cent {
                text-align: center;
            }
            .but {
                text-align: center;
                margin-top: .2%;
            }
        </style>
    </head>
    <body>
        <c:import url="${contextPath}/WEB-INF/view/navigationBar.jsp"/>
        <form method="post" class="center">
          <label for="skillName">Please, enter skill name like Java, C++, C# or JS to view developers:</label><br>
          <input type="text" id="skillName" name="skillName"><br>
          <button type="submit" class="but">Get</button>
        </form>
    </body>
</html>