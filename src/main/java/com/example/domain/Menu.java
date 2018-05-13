package com.example.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menus")
public class Menu extends AbstractBaseEntity {

    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "menu_dish_link", joinColumns = {
            @JoinColumn(name = "menu_id", nullable = false, updatable = false) },
            inverseJoinColumns = {
            @JoinColumn(name = "dish_id", nullable = false, updatable = false) })
    private List<Dish> dishes;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "menu")
    private List<Vote> votes;

    public Menu() {
    }

    public Menu(Menu menu) {
        this(menu.getId(), menu.getDate(), menu.getRestaurant(), menu.getDishes());
    }

    public Menu(Integer id, LocalDate date, Restaurant restaurant, List<Dish> dishes) {
        this.id = id;
        this.date = date;
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
}
