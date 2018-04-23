package com.example.model;

import com.example.HasId;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

public class Menu implements HasId {

    private Integer id;

    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @ManyToMany
    private List<Dish> dishes;

    @OneToMany
    private List<Vote> votes;

    public Menu() {
    }

    public Menu(Menu menu) {
        this(menu.getId(), menu.getDateTime(), menu.getRestaurant(), menu.getDishes(), menu.getVotes());
    }

    public Menu(Integer id, LocalDateTime dateTime, Restaurant restaurant, List<Dish> dishes, List<Vote> votes) {
        this.id = id;
        this.dateTime = dateTime;
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.votes = votes;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
