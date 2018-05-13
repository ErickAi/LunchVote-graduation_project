package com.example.data;

import com.example.domain.Dish;

import java.util.Arrays;

import static com.example.data.MenuTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class DishTestData {

    //data form resources/db/populate_db
    public static final int DISH_1_ID = 100;
    public static final int DISH_2_ID = 101;
    public static final int DISH_3_ID = 102;
    public static final int DISH_4_ID = 103;
    public static final int DISH_5_ID = 104;
    public static final int DISH_6_ID = 105;
    public static final int DISH_7_ID = 106;
    public static final int DISH_8_ID = 107;
    public static final int DISH_9_ID = 108;
    public static final int DISH_10_ID = 109;

    public static final Dish DISH_1 = new Dish(DISH_1_ID, "Burger", 200, Arrays.asList      (PAST_VOTE_EXIST_MENU, FUTURE_NOT_VOTED_MENU));
    public static final Dish DISH_2 = new Dish(DISH_2_ID, "Nuggets", 50, Arrays.asList      (PAST_NOT_VOTED_MENU,FUTURE_FOR_UPDATE_MENU));
    public static final Dish DISH_3 = new Dish(DISH_3_ID, "Sandwich", 120, Arrays.asList    (FUTURE_VOTE_EXIST_MENU));
    public static final Dish DISH_4 = new Dish(DISH_4_ID, "Salad", 150, Arrays.asList       (PAST_VOTE_EXIST_MENU, PAST_NOT_VOTED_MENU, FUTURE_VOTE_EXIST_MENU,FUTURE_FOR_UPDATE_MENU, FUTURE_NOT_VOTED_MENU));
    public static final Dish DISH_5 = new Dish(DISH_5_ID, "Coffee", 160, Arrays.asList      (PAST_VOTE_EXIST_MENU));
    public static final Dish DISH_6 = new Dish(DISH_6_ID, "Tea", DISH_10_ID, Arrays.asList       (PAST_NOT_VOTED_MENU, FUTURE_NOT_VOTED_MENU));
    public static final Dish DISH_7 = new Dish(DISH_7_ID, "Cola", 130, Arrays.asList        (FUTURE_VOTE_EXIST_MENU, FUTURE_FOR_UPDATE_MENU));
    public static final Dish DISH_8 = new Dish(DISH_8_ID, "Chocolate", 170, Arrays.asList   (PAST_VOTE_EXIST_MENU));
    public static final Dish DISH_9 = new Dish(DISH_9_ID, "Cheesecake", 180, Arrays.asList  (PAST_NOT_VOTED_MENU, FUTURE_FOR_UPDATE_MENU));
    public static final Dish DISH_10 = new Dish(DISH_10_ID, "Ice cream", 160, Arrays.asList (FUTURE_VOTE_EXIST_MENU, FUTURE_NOT_VOTED_MENU));


    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "menu", "price");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual)
                .usingElementComparatorIgnoringFields("menu", "price")
                .isEqualTo(expected);
    }
}
