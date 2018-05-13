package com.example.service;

import com.example.domain.Vote;
import com.example.dto.VoteTo;
import com.example.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

import static com.example.data.MenuTestData.*;
import static com.example.data.UserTestData.USER;
import static com.example.data.UserTestData.USER_ID;
import static com.example.data.VoteTestData.*;
import static com.example.data.VoteTestData.assertMatch;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    public void getAll() {
        Collection<Vote> all = service.getAll();
        assertMatch(all, PAST_VOTE, FUTURE_VOTE);
    }

    @Test
    @Transactional
    public void getForUserAndDate() {
        Vote vote = service.getForUserAndDate(USER_ID, PAST_VOTE.getDate()).orElseThrow(() -> new NotFoundException("vote not found"));
        assertMatch(vote, PAST_VOTE);
    }

    @Test
    @Transactional
    public void expiredUpdate() {
        VoteTo expired = service.createOrUpdate(USER_ID, PAST_VOTE_EXIST_MENU);
        assertMatch(expired, UPDATE_EXPIRED_VOTE);
    }

    @Test
    @Transactional
    public void createInThePast() {
        VoteTo forgotten = service.createOrUpdate(USER_ID, PAST_NOT_VOTED_MENU);
        assertMatch(forgotten, NEW_PAST_VOTE);
    }

    @Test
    @Transactional
    public void allowedUpdate() {
        VoteTo updated = service.createOrUpdate(USER_ID, FUTURE_FOR_UPDATE_MENU);
        assertMatch(updated, UPDATED_VOTE);
    }

    @Test
    @Transactional
    public void createNewVote() {
        VoteTo created = service.createOrUpdate(USER_ID, FUTURE_NOT_VOTED_MENU);
        System.out.println();
        assertMatch(created, NEW_FUTURE_VOTE);
    }

    @Test
    @Transactional
    public void save() {
        Vote vote = new Vote(USER, FUTURE_NOT_VOTED_MENU, LocalDate.of(3000, 1, 11));
        Vote created = service.save(vote);
        System.out.println(created);
        assertMatch(created, vote);
    }

    @Test
    @Transactional
    public void update() {
        Vote vote = new Vote(FUTURE_VOTE);
        vote.setMenu(FUTURE_FOR_UPDATE_MENU);
        Vote created = service.save(vote);
        assertMatch(created, vote);
    }

    @Test
    public void delete() {
    }
}