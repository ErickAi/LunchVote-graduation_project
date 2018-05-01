package com.example.data;

import com.example.domain.Restaurant;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    //data form resources/db/populate_db
    public static final int RESTAURANT_1_ID = 10000;
    public static final int RESTAURANT_2_ID = 10001;
    public static final int RESTAURANT_3_ID = 10002;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "Ginza", "Address: Aptekarskiy Ave., 16, St. Petersburg 197022, Russia.");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "Mansarda", "Address: Pochtamtskaya St., 3-5, St. Petersburg 190000, Russia.");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_3_ID, "Terrassa", "Address: Kazanskaya St., 3A, St. Petersburg 191186, Russia");

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "votes", "menus");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorOnFields("id", "name", "description")
                .isEqualTo(expected);
    }
}
