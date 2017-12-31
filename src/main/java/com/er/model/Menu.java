package com.er.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Menu extends AbstractEntity {

    private LocalDateTime dateTime;
    private Restaurant restaurant;
    //strategy to mapping 12.00
    //https://www.youtube.com/watch?v=aXAdLcnniEs&index=4&list=PLoij6udfBnci05Oh7IRN-KU3PCjLeYtez
    private List<Dish> dishes = new CopyOnWriteArrayList<>();
    private Integer votes;
    public Menu() {
    }

    public Menu(List<Dish> dishes) {
        this(null, LocalDateTime.now(), dishes);
    }
    public Menu(Dish... dishes) {
        this(Arrays.asList(dishes));
    }
    public Menu(Integer id, LocalDateTime dateTime, List<Dish> dishes) {

        super(id);
        this.dateTime = dateTime;
        this.dishes = dishes;
    }

    public Menu(int id, LocalDateTime dateTime, List<Dish> dishes, Integer votes) {
        super(id);
        this.dateTime = dateTime;
        this.dishes = dishes;
        this.votes = votes;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
