<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <hr/>
    <form method="post" action="meals?action=filter">
        <div class="form-group row">
            <div class="col-sm-2">
                <label for="startDate">StartDate:</label>
                <input type="date" class="form-control" id="startDate" name="startDate" value="${param.startDate}">
            </div>
            <div class="col-sm-2">
                <label for="endDate">EndDate:</label>
                <input type="date" class="form-control" id="endDate" name="endDate" value="${param.endDate}">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-2">
                <label for="startTime">StartTime:</label>
                <input type="time" class="form-control" id="startTime" name="startTime" value="${param.startTime}">
            </div>
            <div class="col-sm-2">
                <label for="endTime">EndTime:</label>
                <input type="time" class="form-control" id="endTime" name="endTime" value="${param.endTime}">
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Filter</button>
    </form>
    <hr/>
    <a href="meals?action=create">Add Meal</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>