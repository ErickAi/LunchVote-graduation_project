package com.example.data;

import com.example.domain.Menu;
import com.example.dto.MenuTo;

import java.time.LocalDate;
import java.util.Arrays;

import static com.example.data.DishTestData.*;
import static com.example.data.RestaurantTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {

    //data form resources/db/populate_db
    public static final int PAST_VOTE_EXIST_MENU_ID = 1000;
    public static final int PAST_NOT_VOTED_MENU_ID = 1001;
    public static final int CURRENT_VOTE_EXIST_MENU_ID = 1002;
    public static final int CURRENT_FOR_UPDATE_MENU_ID = 1003;
    public static final int FUTURE_NOT_VOTED_MENU_ID = 1004;
    public static final int OLD_EXAMPLE_MENU_ID = 1005;



    public static final Menu PAST_VOTE_EXIST_MENU = new Menu(PAST_VOTE_EXIST_MENU_ID,
            LocalDate.now().minusDays(1), RESTAURANT_1, Arrays.asList(DISH_01, DISH_02, DISH_03));
    public static final Menu PAST_NOT_VOTED_MENU = new Menu(PAST_NOT_VOTED_MENU_ID,
            LocalDate.now().minusDays(1), RESTAURANT_2, Arrays.asList(DISH_04, DISH_05, DISH_06));

    public static final Menu CURRENT_VOTE_EXIST_MENU = new Menu(CURRENT_VOTE_EXIST_MENU_ID,
            LocalDate.now(), RESTAURANT_1, Arrays.asList(DISH_07, DISH_08, DISH_09));
    public static final Menu CURRENT_NOT_VOTED_MENU = new Menu(CURRENT_FOR_UPDATE_MENU_ID,
            LocalDate.now(), RESTAURANT_2, Arrays.asList(DISH_10, DISH_11, DISH_12));
    public static final Menu FUTURE_NOT_VOTED_MENU = new Menu(FUTURE_NOT_VOTED_MENU_ID,
            LocalDate.now().plusDays(1), RESTAURANT_1, Arrays.asList(DISH_13, DISH_14, DISH_15));
    public static final Menu OLD_EXAMPLE_MENU = new Menu(OLD_EXAMPLE_MENU_ID,
            LocalDate.of(2018,01,01), RESTAURANT_1, null);


    public static void assertMatch(Menu actual, Menu expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes");
    }

    public static void assertMatch(MenuTo actual, MenuTo expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "quantityVotes", "dishes", "votes");
    }

    public static void assertMatchVoteList(Iterable<Menu> actual, Iterable<Menu> expected) {
        assertThat(actual).usingElementComparatorOnFields("id", "date", "restaurant")
                .isEqualTo(expected);
    }

    public static void assertMatch(Iterable<MenuTo> actual, Iterable<MenuTo> expected) {
        assertThat(actual).usingElementComparatorOnFields("id", "date", "restaurant")
                .isEqualTo(expected);
    }
}
