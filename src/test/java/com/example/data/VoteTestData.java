package com.example.data;

import com.example.domain.Vote;
import com.example.dto.VoteTo;

import java.util.Arrays;

import static com.example.data.MenuTestData.*;
import static com.example.data.UserTestData.USER;
import static com.example.data.UserTestData.USER_ID;
import static com.example.domain.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class VoteTestData {

    //data form resources/db/populate_db
    public static final int PAST_VOTE_ID = START_SEQ +2;
    public static final int FUTURE_VOTE_ID = START_SEQ +3;

    public static final Vote PAST_VOTE = new Vote(PAST_VOTE_ID,       USER,  PAST_VOTE_EXIST_MENU,   PAST_VOTE_EXIST_MENU.getDate());
    public static final Vote FUTURE_VOTE = new Vote(FUTURE_VOTE_ID,   USER,  FUTURE_VOTE_EXIST_MENU, FUTURE_VOTE_EXIST_MENU.getDate());

    public static final VoteTo NEW_PAST_VOTE = new VoteTo(START_SEQ +4, USER_ID, PAST_NOT_VOTED_MENU,true);
    public static final VoteTo UPDATE_EXPIRED_VOTE = new VoteTo(PAST_VOTE_ID, USER_ID, PAST_VOTE_EXIST_MENU, false);
    public static final VoteTo NEW_FUTURE_VOTE = new VoteTo(START_SEQ +4, USER_ID, FUTURE_NOT_VOTED_MENU, true);
    public static final VoteTo UPDATED_VOTE = new VoteTo(FUTURE_VOTE_ID, USER_ID, FUTURE_FOR_UPDATE_MENU, true);

    public static void assertMatch(VoteTo actual, VoteTo expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,  "currentTime");
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,  "user");
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
