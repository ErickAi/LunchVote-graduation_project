package com.er.service;


import com.er.model.Restaurant;
import com.er.util.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantService {

    Restaurant create(Restaurant restaurant, int userId);

    Restaurant get(int id, int userId) throws NotFoundException;

    Restaurant update(Restaurant restaurant, int userId) throws NotFoundException;

    void delete(int id, int userId) throws NotFoundException;

    List<Restaurant> getRestaurantRange(LocalDateTime dateTime);

}