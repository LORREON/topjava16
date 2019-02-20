package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MealsUtil {

    public static final List<Meal> MEAL_LIST = Arrays.asList(
            new Meal(LocalDateTime.of(2019, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2019, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2019, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2019, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2019, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2019, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public static void main(String[] args) {
        List<MealTo> mealsWithExcess = getFilteredWithExcess(MEAL_LIST, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsWithExcess.forEach(System.out::println);
    }

    public static List<MealTo> getFilteredWithExcess(List<Meal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Collection<List<Meal>> list = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate)).values();

        return list.stream().flatMap(dayMeals -> {
            boolean excess = dayMeals.stream().mapToInt(Meal::getCalories).sum() > caloriesPerDay;
            return dayMeals.stream().filter(meal ->
                    TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                    .map(meal -> createWithExcess(meal, excess));
        }).collect(toList());
    }

    public static MealTo createWithExcess(Meal meal, boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}