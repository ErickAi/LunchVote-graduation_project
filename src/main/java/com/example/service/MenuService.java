package com.example.service;

import com.example.dao.MenuRepository;
import com.example.dao.VoteRepository;
import com.example.domain.Menu;
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

    @Transactional
    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    public MenuTo get(int id) throws NotFoundException {
        MenuTo menuTo = menuRepository.findById(id).map(MenuUtil::menuAsTo).orElseThrow(() -> new NotFoundException(NOT_FOUND_WITH_ID + id));
        menuTo.setQuantityVotes(voteRepository.countByMenuId(id));
        return menuTo;
    }

    public List<MenuTo> getAll() {
        List<MenuTo> menuTos = MenuUtil.menusAsListTo(menuRepository.findAll());
        for (MenuTo menu : menuTos) {
            menu.setQuantityVotes(voteRepository.countByMenuId(menu.getId()));
        }
        menuTos.sort(Comparator.comparing(MenuTo::getDate).reversed());
        return menuTos;
    }

    @Transactional
    public void update(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        checkNotFound(menuRepository.save(menu), NOT_FOUND_WITH_ID + menu.getId());
    }

/*
    @Transactional
    public void delete(Integer id) {
        menuRepository.deleteById(id);
    }
*/

    public List<MenuTo> getAllForDate(LocalDate date) {
        List<MenuTo> menuTos = menuRepository.getAllForDate(date).map(MenuUtil::menusAsListTo)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_WITH + date));
        for (MenuTo menu : menuTos) {
            menu.setQuantityVotes(voteRepository.countByMenuId(menu.getId()));
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
