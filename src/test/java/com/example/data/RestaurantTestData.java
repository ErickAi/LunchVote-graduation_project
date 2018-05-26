package com.example.data;

import com.example.domain.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    //data form resources/db/populate_db
    public static final int RESTAURANT_1_ID = 10000;
    public static final int RESTAURANT_2_ID = 10001;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "Mansarda");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "Terrassa");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,  "menus");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorOnFields("id", "name", "description")
                .isEqualTo(expected);
    }
}
