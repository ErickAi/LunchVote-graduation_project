package com.example.web;

import com.example.TestUtil;
import com.example.dao.RestaurantRepository;
import com.example.domain.Restaurant;
import com.example.util.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.example.TestUtil.userHttpBasic;
import static com.example.data.RestaurantTestData.*;
import static com.example.data.UserTestData.ADMIN;
import static com.example.data.UserTestData.USER;
import static com.example.data.RestaurantTestData.assertMatch;
import static com.example.data.RestaurantTestData.contentJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.RESTAURANT_URL + '/';

    @Autowired
    RestaurantRepository repository;


    @Test
    public void create() throws Exception {
        Restaurant expected = new Restaurant(null, "New Restaurant");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated())
                .andDo(print());

        Restaurant returned = TestUtil.readFromJson(action, Restaurant.class);
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
                .andExpect(contentJson(RESTAURANT_1, RESTAURANT_2));
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_1));
    }

    @Test
    public void update() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_2);
        updated.setName("UpdatedName");
        ResultActions action = mockMvc.perform(put(REST_URL + RESTAURANT_2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        Restaurant returned = repository.getOne(RESTAURANT_2_ID);
        assertMatch(updated, returned);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete((REST_URL + RESTAURANT_2_ID))
                .with(TestUtil.userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void findByName() throws Exception {
        mockMvc.perform(get(REST_URL + "find-by-name")
                .with(userHttpBasic(USER))
                .param("name", "Man"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJsonAsSingletoneList(RESTAURANT_1))
                .andDo(print());
    }

    @Test
    public void getNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void updateUnauthorizedFailed() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_2);
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + RESTAURANT_2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    public void updateAccessDenied() throws Exception {
        Restaurant updated = new Restaurant(RESTAURANT_2);
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + RESTAURANT_2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isForbidden())
                .andDo(print());

    }
}