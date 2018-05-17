package com.example.service;

import com.example.domain.Vote;
import com.example.dto.VoteTo;
import com.example.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.example.data.MenuTestData.CURRENT_NOT_VOTED_MENU;
import static com.example.data.MenuTestData.FUTURE_NOT_VOTED_MENU;
import static com.example.data.UserTestData.USER_ID;
import static com.example.data.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;


    @Test
    public void getForUserAndDate() {
        Vote vote = service.getForUserAndDate(USER_ID, PAST_VOTE.getDate()).orElseThrow(() -> new NotFoundException("vote not found"));
        assertMatch(vote, PAST_VOTE);
    }

    @Test
    public void allowedUpdate() {
        VoteTo voteTo = new VoteTo(USER_ID, CURRENT_NOT_VOTED_MENU);
        voteTo.setCurrentTime(LocalDate.now().atTime(10,59));
        System.out.println(voteTo.getCurrentTime());
        VoteTo updated = service.createOrUpdate(voteTo);
        assertMatch(updated, voteTo);
    }

    @Test
    public void expiredUpdate() {
        VoteTo voteTo = new VoteTo(USER_ID, CURRENT_NOT_VOTED_MENU);
        voteTo.setCurrentTime(LocalDate.now().atTime(11, 01));
        VoteTo expired = service.createOrUpdate(voteTo);
        assertMatch(expired, new VoteTo(CURRENT_VOTE));
    }

    @Test
    public void createNewVote() {
        VoteTo voteTo = new VoteTo(USER_ID, FUTURE_NOT_VOTED_MENU);
        VoteTo created = service.createOrUpdate(voteTo);
        assertMatch(created, voteTo);
    }
}