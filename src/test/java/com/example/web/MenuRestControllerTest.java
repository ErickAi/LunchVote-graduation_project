package com.example.web;

import com.example.TestUtil;
import com.example.dao.MenuRepository;
import com.example.domain.Menu;
import com.example.service.MenuService;
import com.example.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static com.example.TestUtil.userHttpBasic;
import static com.example.data.MenuTestData.*;
import static com.example.data.RestaurantTestData.RESTAURANT_1_ID;
import static com.example.data.RestaurantTestData.RESTAURANT_2;
import static com.example.data.UserTestData.ADMIN;
import static com.example.data.UserTestData.USER;
import static com.example.web.MenuRestController.MENU_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MenuRestControllerTest extends AbstractControllerTest{

    private static final String REST_URL = MENU_URL + '/';
    
    @Autowired
    MenuService service;
    @Autowired
    MenuRepository repository;

    @Test
    public void create() throws Exception {
        Menu expected = new Menu(LocalDate.now().plusDays(1), RESTAURANT_2);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated())
                .andDo(print());

        Menu returned = TestUtil.readFromJson(action, Menu.class);
        expected.setId(returned.getId());
        assertMatch(expected, returned);
        }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(PAST_VOTE_EXIST_MENU, PAST_NOT_VOTED_MENU, CURRENT_VOTE_EXIST_MENU, CURRENT_NOT_VOTED_MENU, FUTURE_NOT_VOTED_MENU, OLD_EXAMPLE_MENU));
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get(REST_URL + PAST_VOTE_EXIST_MENU_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(PAST_VOTE_EXIST_MENU));
    }

    @Test
    public void update() throws Exception {
        Menu updated = new Menu(FUTURE_NOT_VOTED_MENU);
        updated.setRestaurant(RESTAURANT_2);
        mockMvc.perform(put(REST_URL + FUTURE_NOT_VOTED_MENU_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        Menu returned = repository.getOne(FUTURE_NOT_VOTED_MENU_ID);
        assertMatch(updated, returned);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete((REST_URL + FUTURE_NOT_VOTED_MENU_ID))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllForDate() throws Exception{
            mockMvc.perform(get(REST_URL + "for-date")
                .with(userHttpBasic(USER))
            .param("date", LocalDate.now().minusDays(1).toString()))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(contentJson(PAST_VOTE_EXIST_MENU, PAST_NOT_VOTED_MENU))
            .andDo(print());
}

    @Test
    public void getAllForCurrentDate() throws Exception{
        mockMvc.perform(get(REST_URL + "for-date")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(CURRENT_VOTE_EXIST_MENU, CURRENT_NOT_VOTED_MENU))
                .andDo(print());
    }

    @Test
    public void getAllForRestaurant() throws Exception{
        mockMvc.perform(get(REST_URL + "for-restaurant/" + RESTAURANT_1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(contentJson(CURRENT_VOTE_EXIST_MENU, FUTURE_NOT_VOTED_MENU, PAST_VOTE_EXIST_MENU, OLD_EXAMPLE_MENU));
    }
}