<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meal</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <input type="hidden" name="id" value="${meal.id}">

        <div class="form-group row">
            <div class="col-sm-2">
                <label for="dateTime">DateTime:</label>
                <input type="datetime-local" value="${meal.dateTime}" name="dateTime" id="dateTime" class="form-control"
                       required></div>
        </div>

        <div class="form-group row">
            <div class="col-sm-4">
                <label for="description">Description:</label>
                <input type="text" value="${meal.description}" size=40 name="description" id="description"
                       class="form-control" required>
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-4">
                <label for="calories">Calories:</label>
                <input type="number" value="${meal.calories}" name="calories" required id="calories"
                       class="form-control" required>
            </div>
        </div>

        <button type="submit" class="btn btn-primary" required>Save</button>
        <button onclick="window.history.back()" type="button" class="btn btn-secondary">Cancel</button>
    </form>
</div>
</body>
</html>
