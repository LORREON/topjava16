package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceededByStream2(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredWithExceeded.forEach(System.out::println);

    }

    public static List<UserMealWithExceed> getFilteredWithExceededByCycle(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> map = new HashMap<>();

        for (UserMeal meal : mealList) {
            map.merge(meal.getDate(), meal.getCalories(), (cal1, cal2) -> cal1 + cal2);
        }

        List<UserMealWithExceed> list = new ArrayList<>();
        for (UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                list.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        map.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return list;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));
        //.collect(Collectors.toMap(UserMeal::getDate, UserMeal::getCalories, Integer::sum))

        return mealList.stream()
                .filter(date -> TimeUtil.isBetween(date.getTime(), startTime, endTime))
                .map(map -> new UserMealWithExceed(map.getDateTime(), map.getDescription(), map.getCalories(),
                        caloriesSumByDate.get(map.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededByStream2(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        return mealList.stream().collect(Collectors.groupingBy(UserMeal::getDate)).values()
                .stream().flatMap(dayMeals -> {
                    boolean exceed = dayMeals.stream().mapToInt(UserMeal::getCalories).sum() > caloriesPerDay;
                    return dayMeals.stream().filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                            .map(meal -> new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceed));
                })
                .collect(Collectors.toList());
    }

    //    //Edit UserMealWithExceed.exceed with boolean to AtomicBoolean
//    public static List<UserMealWithExceed> getFilteredWithExceededByCycle2(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
//        Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
//        Map<LocalDate, AtomicBoolean> exceededMap = new HashMap<>();
//
//        List<UserMealWithExceed> mealWithExceeds = new ArrayList<>();
//
//        mealList.forEach(meal -> {
//            AtomicBoolean wrapBoolean = exceededMap.computeIfAbsent(meal.getDate(), date -> new AtomicBoolean());
//            Integer dailyCalories = caloriesSumByDate.merge(meal.getDate(), meal.getCalories(), Integer::sum);
//            if (dailyCalories > caloriesPerDay) {
//                wrapBoolean.set(true);
//            }
//
//            if (TimeUtil.isBetween(meal.getTime(), startTime, endTime)) {
//                mealWithExceeds.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), wrapBoolean));
//            }
//        });
//
//        return mealWithExceeds;
//    }
}
