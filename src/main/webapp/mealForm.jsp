<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>


<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
<form action="meals" method="post">
    <fieldset style="width: 200px; color: blue">
        <legend>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</legend>
        <p>
            <input type="hidden" name="id" value="${meal.id}">
        </p>
        <p>
            <label>Data</label><br>
            <input type="datetime-local" name="dateTime" value="${meal.dateTime}" required>
        </p>
        <p>
            <label>Description</label><br>
            <input type="text" name="description" value="${meal.description}" required>
        </p>
        <p>
            <label>Calories</label><br>
            <input type="number" name="calories" value="${meal.calories}" required>
        </p>
        <p>
            <button type="submit">Save</button>
            <button onclick="window.history.back()" type="button">Cancel</button>
        </p>
    </fieldset>
</form>

</body>
</html>
