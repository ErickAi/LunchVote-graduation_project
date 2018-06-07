package com.example.service;

import com.example.dao.DishRepository;
import com.example.dao.MenuRepository;
import com.example.domain.Menu;
import com.example.dto.MenuTo;
import com.example.util.MenuUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.example.data.MenuTestData.*;
import static com.example.data.RestaurantTestData.RESTAURANT_1_ID;
import static com.example.data.RestaurantTestData.RESTAURANT_2;

public class MenuServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuService service;

    @Autowired
    private MenuRepository repository;

    @Autowired
    DishRepository dishRepository;

    @Test
    public void create() {
        Menu menu = new Menu(LocalDate.now().plusDays(1), RESTAURANT_2);
        Menu created = service.createOrUpdate(menu);
        assertMatchVoteList(repository.findAll(), Arrays.asList(
                PAST_VOTE_EXIST_MENU, PAST_NOT_VOTED_MENU, CURRENT_VOTE_EXIST_MENU, CURRENT_NOT_VOTED_MENU, FUTURE_NOT_VOTED_MENU, OLD_EXAMPLE_MENU, created));
    }

    @Test
    public void get() {
        MenuTo menu = service.get(CURRENT_VOTE_EXIST_MENU_ID);
        assertMatch(menu, MenuUtil.menuAsTo(CURRENT_VOTE_EXIST_MENU));
    }

    @Test
    public void getAll() {
        List<MenuTo> menus = service.getAll();
        List<MenuTo> expected = MenuUtil.menusAsListTo(Arrays.asList(
                FUTURE_NOT_VOTED_MENU, CURRENT_VOTE_EXIST_MENU, CURRENT_NOT_VOTED_MENU, PAST_VOTE_EXIST_MENU, PAST_NOT_VOTED_MENU, OLD_EXAMPLE_MENU));
        assertMatch(menus, expected);
    }

    @Test
    @Transactional
    public void update() {
        Menu forUpdate = new Menu(CURRENT_VOTE_EXIST_MENU);
        forUpdate.setDate(LocalDate.now().plusDays(2));
        service.createOrUpdate(forUpdate);
        Menu updated = repository.getOne(CURRENT_VOTE_EXIST_MENU_ID);
        assertMatch(updated, forUpdate);
    }

    @Test
    public void getAllForDate() {
        List<MenuTo> menus = service.getAllForDate(CURRENT_VOTE_EXIST_MENU.getDate());
        List<MenuTo> expected = MenuUtil.menusAsListTo(Arrays.asList(CURRENT_VOTE_EXIST_MENU, CURRENT_NOT_VOTED_MENU));
        assertMatch(menus, expected);
    }

    @Test
    public void getAllForRestaurant() {
        List<MenuTo> menus = service.getAllForRestaurant(RESTAURANT_1_ID);
        List<MenuTo> expected = MenuUtil.menusAsListTo(Arrays.asList(OLD_EXAMPLE_MENU, PAST_VOTE_EXIST_MENU, CURRENT_VOTE_EXIST_MENU, FUTURE_NOT_VOTED_MENU));
        assertMatch(menus, expected);
    }


}