package com.example.service;

import com.example.dao.MenuRepository;
import com.example.dao.RestaurantRepository;
import com.example.dao.VoteRepository;
import com.example.domain.Menu;
import com.example.domain.Restaurant;
import com.example.dto.MenuTo;
import com.example.util.MenuUtil;
import com.example.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static com.example.util.ValidationUtil.*;

@Service("menuService")
@Transactional(readOnly = true)
public class MenuService {

    @Autowired
    MenuRepository menuRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

/*
    @Transactional
    public Menu create(Menu menu, int restaurantId) {
        menu.setRestaurant(restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Not found restaurant " + restaurantId + " to create a menu")));
        return menuRepository.save(menu);
    }
*/

    @Transactional
    public Menu createOrUpdate(Menu menu) {

        Restaurant restaurant = menu.getRestaurant();
        assureIdConsistent(restaurant, restaurantRepository.getByName(restaurant.getName()));
        menu.setRestaurant(restaurantRepository.save(restaurant));

        assureIdConsistent(menu, menuRepository.getByDateAndRestaurant(menu.getDate(), menu.getRestaurant()));
        return menuRepository.save(menu);
    }

    public List<MenuTo> getAll() {
        List<MenuTo> menuTos = MenuUtil.menusAsListTo(menuRepository.findAll());
        for (MenuTo menu : menuTos) {
            menu.setQuantityVotes(voteRepository.countByMenuId(menu.getId()));
        }
        menuTos.sort(Comparator.comparing(MenuTo::getDate).reversed());
        return menuTos;
    }

    public MenuTo get(int id) throws NotFoundException {
        MenuTo menuTo = menuRepository.findById(id).map(MenuUtil::menuAsTo).orElseThrow(() -> new NotFoundException(NOT_FOUND_WITH_ID + id));
        menuTo.setQuantityVotes(voteRepository.countByMenuId(id));
        return menuTo;
    }

    @Transactional
    public void delete(Integer id) {
        menuRepository.deleteById(id);
    }

    public List<MenuTo> getAllForDate(LocalDate date) {
        List<Menu> menus = menuRepository.getAllForDate(date)
                .orElseGet(() -> menuRepository.getAllForDate(LocalDate.now())
                        .orElseThrow(() -> new NotFoundException(NOT_FOUND_WITH + date)));
        List<MenuTo> menuTos = MenuUtil.menusAsListTo(menus);
        for (MenuTo menuTo : menuTos) {
            menuTo.setQuantityVotes(voteRepository.countByMenuId(menuTo.getId()));
        }
        menuTos.sort(Comparator.comparing(MenuTo::getQuantityVotes).reversed());
        return menuTos;
    }

    public List<MenuTo> getAllForRestaurant(int restaurantId) {
        List<MenuTo> menuTos = menuRepository.getAllForRestaurant(restaurantId).map(MenuUtil::menusAsListTo)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_WITH + "restaurant " + restaurantId));
        for (MenuTo menu : menuTos) {
            menu.setQuantityVotes(voteRepository.countByMenuId(menu.getId()));
        }
        menuTos.sort(Comparator.comparing(MenuTo::getDate));
        return menuTos;
    }
}
