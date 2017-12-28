package com.er.repository;

import com.er.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    boolean delete(int id);

    Restaurant get(int id);

    List<Restaurant> getRestaurantRange(LocalDateTime dateTime);
}
