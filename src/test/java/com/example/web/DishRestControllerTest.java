package com.example.web;

import com.example.TestUtil;
import com.example.dao.DishRepository;
import com.example.domain.Dish;
import com.example.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static com.example.TestUtil.userHttpBasic;
import static com.example.data.DishTestData.*;
import static com.example.data.DishTestData.assertMatch;
import static com.example.data.DishTestData.contentJson;
import static com.example.data.MenuTestData.*;
import static com.example.data.UserTestData.ADMIN;
import static com.example.data.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DishRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishRestController.DISH_URL + '/';
    
    @Autowired
    DishRepository repository;

    @Test
    public void create() throws Exception {
        Dish expected = new Dish(null,"New Dish", 180, FUTURE_EXAMPLE_MENU);
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated())
                .andDo(print());

        Dish returned = TestUtil.readFromJson(action, Dish.class);
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
        ;
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get(REST_URL + DISH_13_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DISH_13));
    }

    @Test
    public void update() throws Exception {
        Dish updated = new Dish(DISH_14);
        updated.setName("UpdatedName");
        updated.setMenu(FUTURE_NOT_VOTED_MENU);
        mockMvc.perform(put(REST_URL + DISH_14_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        Dish returned = repository.getOne(DISH_14_ID);
        assertMatch(updated, returned);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete((REST_URL + DISH_12_ID))
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
                .andExpect(contentJson(DISH_01, DISH_02, DISH_03))
                .andDo(print());
    }

    @Test
    public void getAllForCurrentDate() throws Exception{
        System.out.println(DISH_11);
        mockMvc.perform(get(REST_URL + "for-date")
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DISH_04, DISH_05, DISH_06, DISH_07, DISH_08, DISH_09))
                .andDo(print());
    }

    @Test
    public void getAllForMenu() throws Exception{
        mockMvc.perform(get(REST_URL + "for-menu/" + CURRENT_VOTE_EXIST_MENU_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(contentJson(DISH_04, DISH_05, DISH_06));
    }
}