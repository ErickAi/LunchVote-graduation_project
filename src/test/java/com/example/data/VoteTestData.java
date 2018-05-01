package com.example.data;

import com.example.domain.Vote;

import java.time.LocalDateTime;
import java.util.Arrays;

import static com.example.data.RestaurantTestData.*;
import static com.example.data.UserTestData.*;
import static com.example.domain.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class VoteTestData {

    //data form resources/db/populate_db
    public static final int VOTE_1_ID = START_SEQ +2;
    public static final int VOTE_2_ID = START_SEQ +3;

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, LocalDateTime.of(2018, 05, 1, 8, 0, 0), ADMIN, RESTAURANT_1);
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, LocalDateTime.of(2018, 05, 1, 9, 0, 0), USER, RESTAURANT_2);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "voteTime", "restaurant", "user");
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual)
                .usingElementComparatorIgnoringFields("voteTime", "restaurant", "user")
                .isEqualTo(expected);
    }
}
