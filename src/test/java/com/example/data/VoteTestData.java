package com.example.data;

import com.example.domain.Vote;
import com.example.dto.VoteTo;

import java.util.Arrays;

import static com.example.data.MenuTestData.CURRENT_VOTE_EXIST_MENU;
import static com.example.data.MenuTestData.PAST_VOTE_EXIST_MENU;
import static com.example.data.UserTestData.USER;
import static com.example.domain.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class VoteTestData {

    //data form resources/db/populate_db
    public static final int PAST_VOTE_ID = START_SEQ +2;
    public static final int CURRENT_VOTE_ID = START_SEQ +3;

    public static final Vote PAST_VOTE = new Vote(PAST_VOTE_ID,       USER,  PAST_VOTE_EXIST_MENU);
    public static final Vote CURRENT_VOTE = new Vote(CURRENT_VOTE_ID, USER, CURRENT_VOTE_EXIST_MENU);

    public static void assertMatch(VoteTo actual, VoteTo expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,  "currentTime");
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringNullFields(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual)
                .usingElementComparatorIgnoringFields("menu", "user")
                .isEqualTo(expected);
    }
}
