package com.er.repository;

import com.er.model.Dish;

import java.time.LocalDateTime;
import java.util.List;

public interface MenuRepository {

    List<Dish> getMenu(int RestaurantId, LocalDateTime date);

    int getVotesForMenu(int RestaurantId, LocalDateTime date);

}
