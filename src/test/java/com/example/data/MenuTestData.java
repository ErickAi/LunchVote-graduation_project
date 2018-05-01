package com.example.data;

import com.example.domain.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.data.RestaurantTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MenuTestData {

    //data form resources/db/populate_db
    public static final int MENU_1_ID = 1000;
    public static final int MENU_2_ID = 1001;
    public static final int MENU_3_ID = 1002;
    public static final int MENU_4_ID = 1003;

    public static final Menu MENU_1 = new Menu(MENU_1_ID, LocalDate.of(2018, 05, 1), RESTAURANT_1, new ArrayList<>());
    public static final Menu MENU_2 = new Menu(MENU_2_ID, LocalDate.of(2018, 05, 1), RESTAURANT_2, new ArrayList<>());
    public static final Menu MENU_3 = new Menu(MENU_3_ID, LocalDate.of(2018, 05, 2), RESTAURANT_1, new ArrayList<>());
    public static final Menu MENU_4 = new Menu(MENU_4_ID, LocalDate.of(2018, 05, 2), RESTAURANT_3, new ArrayList<>());


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
