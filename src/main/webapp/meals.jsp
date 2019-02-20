<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        TH, TD {
            border: 1px solid black; /* Параметры рамки */
            padding: 4px; /* Поля вокруг текста */
            text-align: center;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>


<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Дата</th>
        <th>Назва</th>
        <th>Калорії</th>
    </tr>
    </thead>

    <c:forEach items="${mealList}" var="meal">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr style="color: ${meal.excess ?"red":"green"}">
            <td><%= TimeUtil.toString(meal.getDateTime())%></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
