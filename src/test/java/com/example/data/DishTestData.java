package com.example.data;

import com.example.domain.Dish;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DishTestData {
    //data form resources/db/populate_db
    public static final int DISH_01_ID = 100;
    public static final int DISH_02_ID = 101;
    public static final int DISH_03_ID = 102;
    public static final int DISH_04_ID = 103;
    public static final int DISH_05_ID = 104;
    public static final int DISH_06_ID = 105;
    
    public static final int DISH_07_ID = 106;
    public static final int DISH_08_ID = 107;
    public static final int DISH_09_ID = 108;
    public static final int DISH_10_ID = 109;
    public static final int DISH_11_ID = 100;
    public static final int DISH_12_ID = 111;
    
    public static final int DISH_13_ID = 112;
    public static final int DISH_14_ID = 113;
    public static final int DISH_15_ID = 114;
/*

  (106, "Burger",   200, 1002), --future vote exist
  (107, "Coffee",   160, 1002),
  (108, "Ice cream",160, 1002),
  
  (109, "Sandwich", 120, 1003), --future for update
  (110, "Cola",     130, 1003),
  (111, "Chocolate",170, 1003),
  */
//past vote exist
    public static final Dish DISH_01 = new Dish(DISH_01_ID, "Burger",   200);
    public static final Dish DISH_02 = new Dish(DISH_02_ID, "Coffee",   160);
    public static final Dish DISH_03 = new Dish(DISH_03_ID, "Ice cream",160);
//past not voted
    public static final Dish DISH_04 = new Dish(DISH_04_ID, "Nuggets",  160);
    public static final Dish DISH_05 = new Dish(DISH_05_ID, "Tea",      110);
    public static final Dish DISH_06 = new Dish(DISH_06_ID, "Cheesecake",180);
//future vote exist
    public static final Dish DISH_07 = new Dish(DISH_07_ID, "Burger",   200);
    public static final Dish DISH_08 = new Dish(DISH_08_ID, "Coffee",   160);
    public static final Dish DISH_09 = new Dish(DISH_09_ID, "Ice cream",160);
//future for update
    public static final Dish DISH_10 = new Dish(DISH_10_ID, "Sandwich", 120);
    public static final Dish DISH_11 = new Dish(DISH_11_ID, "Cola",     130);
    public static final Dish DISH_12 = new Dish(DISH_12_ID, "Chocolate",170);
//future not voted
    public static final Dish DISH_13 = new Dish(DISH_13_ID, "Nuggets",  160);
    public static final Dish DISH_14 = new Dish(DISH_14_ID, "Tea",      110);
    public static final Dish DISH_15 = new Dish(DISH_15_ID, "Cheesecake",180);

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
