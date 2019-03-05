package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealService {
    Meal create(Meal meal);

    Meal update(Meal meal);

    void delete(int id) throws NotFoundException;

    Meal get(int id) throws NotFoundException;

    Collection<Meal> getAll(int userId);

    List<Meal> getBetweenByDateTime(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);

    default List<Meal> getBetweenByDate(LocalDate startDate, LocalDate endDate, int userId) {
        return getBetweenByDateTime(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }
}