package com.example.util;

import com.example.domain.Menu;
import com.example.dto.MenuTo;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {
    public static List<MenuTo> menusAsListTo(List<Menu> menus) {
        List<MenuTo> menuTos = new ArrayList<>();
        for (Menu menu : menus) {
            menuTos.add(menuAsTo(menu));
        }
        return menuTos;
    }

    public static MenuTo menuAsTo(Menu menu) {
        return new MenuTo(menu.getId(), menu.getDate(), menu.getRestaurant(), menu.getDishes());
    }

}
