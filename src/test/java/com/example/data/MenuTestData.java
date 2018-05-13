package com.example.data;

import com.example.domain.Menu;

import java.time.LocalDate;
import java.util.Arrays;

import static com.example.data.DishTestData.*;
import static com.example.data.RestaurantTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {

    //data form resources/db/populate_db
    public static final int PAST_VOTE_EXIST_MENU_ID = 1000;
    public static final int PAST_NOT_VOTED_MENU_ID = 1001;
    public static final int FUTURE_VOTE_EXIST_MENU_ID = 1002;
    public static final int FUTURE_FOR_UPDATE_MENU_ID = 1003;
    public static final int FUTURE_NOT_VOTED_MENU_ID = 1004;


    public static final Menu PAST_VOTE_EXIST_MENU = new Menu(PAST_VOTE_EXIST_MENU_ID,
            LocalDate.of(2000, 1, 1), RESTAURANT_1, Arrays.asList(DISH_1, DISH_4, DISH_5, DISH_8));
    public static final Menu PAST_NOT_VOTED_MENU = new Menu(PAST_NOT_VOTED_MENU_ID,
            LocalDate.of(2000, 1, 2), RESTAURANT_2, Arrays.asList(DISH_2, DISH_4, DISH_6, DISH_9));

    public static final Menu FUTURE_VOTE_EXIST_MENU = new Menu(FUTURE_VOTE_EXIST_MENU_ID,
            LocalDate.of(3000, 1, 1), RESTAURANT_1, Arrays.asList(DISH_3, DISH_4, DISH_7, DISH_10));
    public static final Menu FUTURE_FOR_UPDATE_MENU = new Menu(FUTURE_FOR_UPDATE_MENU_ID,
            LocalDate.of(3000, 1, 1), RESTAURANT_2, Arrays.asList(DISH_2, DISH_4, DISH_7, DISH_9));
    public static final Menu FUTURE_NOT_VOTED_MENU = new Menu(FUTURE_NOT_VOTED_MENU_ID,
            LocalDate.of(3000, 1, 2), RESTAURANT_3, Arrays.asList(DISH_1, DISH_4, DISH_6, DISH_10));


    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes", "restaurant");
    }

    public static void assertMatch(Iterable<Menu> actual, Menu... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorOnFields("id", "date")
                .isEqualTo(expected);
    }
}
