package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public abstract class AbstractMealController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        int calories = SecurityUtil.authUserCaloriesPerDay();
        log.info("getAll for user {}", userId);
        return MealsUtil.getWithExcess(service.getAll(userId), calories);
    }

    public Meal get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get meal {} for user {}", id, userId);
        return service.get(id);
    }

    public Meal create(Meal meal) {
        int userId = SecurityUtil.authUserId();
        meal.setUserId(userId);
        log.info("create {} for user {}", meal, userId);
        return service.create(meal);
    }

    public Meal update(Meal meal, int id) {
        int userId = SecurityUtil.authUserId();
        meal.setId(id);
        meal.setUserId(userId);
        log.info("update {} for user {}", meal, userId);
        return service.update(meal);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete {} for user {}", id, userId);
        service.delete(id);
    }

    public List<MealTo> getBetween(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        int calories = SecurityUtil.authUserCaloriesPerDay();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}\", startDate, endDate, startTime, endTime, userId");

        List<Meal> mealsDateFiltered = service.getBetweenByDate(startDate != null ? startDate : LocalDate.MIN,
                endDate != null ? endDate : LocalDate.MAX, userId);

        return MealsUtil.getFilteredWithExcess(mealsDateFiltered, calories,
                startTime != null ? startTime : LocalTime.MIN, endTime != null ? endTime : LocalTime.MAX);
    }
}
