package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_date", "restaurant_id"}, name = "unique_menu")})
public class Menu extends AbstractBaseEntity {

    @NotNull
    @Column(name = "menu_date", nullable = false)
    private LocalDate date;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "menu")
    private List<Dish> dishes;

    public Menu() {
    }

    public Menu(Menu menu) {
        this(menu.getId(), menu.getDate(), menu.getRestaurant(), menu.getDishes());
    }

    public  Menu(LocalDate date) {
        this.date = date;
    }

    public  Menu(int id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public  Menu(LocalDate date, Restaurant restaurant) {
        this.date = date;
        this.restaurant = restaurant;
    }

    public  Menu(LocalDate date, Restaurant restaurant, List<Dish> dishes) {
        this(date, restaurant);
        this.dishes = dishes;
    }

    public Menu(Integer id, LocalDate date, Restaurant restaurant, List<Dish> dishes) {
        this(date, restaurant, dishes);
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

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", " + date +
                ", " + restaurant +
                ", dishes=" + dishes +
                '}';
    }
}
