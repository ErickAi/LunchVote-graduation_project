package com.example.dto;

import com.example.domain.Dish;
import com.example.domain.Restaurant;

import java.time.LocalDate;
import java.util.List;

public class MenuTo extends BaseTo {

    private LocalDate date;

    private Restaurant restaurant;

    private List<Dish> dishes;

    private int quantityVotes;

    public MenuTo(Integer id, LocalDate date, Restaurant restaurant, List<Dish> dishes) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getQuantityVotes() {
        return quantityVotes;
    }

    public void setQuantityVotes(int quantityVotes) {
        this.quantityVotes = quantityVotes;
    }

    @Override
    public String toString() {
        return "MenuTo{" +
                "id=" + id +
                ", date=" + date +
                ", quantityVotes=" + quantityVotes +
                '}';
    }
}
