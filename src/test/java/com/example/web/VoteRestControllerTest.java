package com.example.web;

import com.example.service.VoteService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalTime;

import static com.example.TestUtil.userHttpBasic;
import static com.example.data.MenuTestData.*;
import static com.example.data.RestaurantTestData.contentJson;
import static com.example.data.UserTestData.USER;
import static com.example.dto.VoteTo.EXPIRED_TIME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.VOTE_URL + '/';

    @Autowired
    VoteService service;

    @Test
    public void current() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(CURRENT_VOTE_EXIST_MENU.getRestaurant()));
    }

    @Test
    public void createNewVote() throws Exception {
        mockMvc.perform(post(REST_URL + "for-menu/" + FUTURE_NOT_VOTED_MENU_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(FUTURE_NOT_VOTED_MENU.getRestaurant()))
                .andDo(print());
    }

    @Test
    public void dateExpired() throws Exception {
        mockMvc.perform(post(REST_URL + "for-menu/" + PAST_NOT_VOTED_MENU_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        ;
    }

    @Test
    public void voteAtCurrentDate() throws Exception {
        ResultActions action = mockMvc.perform(post(REST_URL + "for-menu/" + CURRENT_NOT_VOTED_MENU_ID)
                .with(userHttpBasic(USER)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print());

        if (LocalTime.now().isBefore(EXPIRED_TIME)) {
            action.andExpect(status().isOk())
                    .andExpect(contentJson(CURRENT_NOT_VOTED_MENU.getRestaurant()));
        } else {
            action.andExpect(status().isNotModified())
                    .andExpect(contentJson(CURRENT_VOTE_EXIST_MENU.getRestaurant()));
        }
    }
}