package com.er.model;

import java.util.Arrays;
import java.util.List;

public class Restaurant extends AbstractEntity{
    private int countVotes;
    private List<Dish> dishes;

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(name);
    }

    public Restaurant(String name, Dish... dishes) {
        super(name);
        this.dishes = Arrays.asList(dishes);
    }
    public Restaurant(String name, List<Dish> dishes) {
        super(name);
        this.dishes = dishes;
    }

    public Restaurant(int id, String name) {
        super(id, name);
    }

    public Restaurant(int id, String name, int countVotes, List<Dish> dishes) {
        super(id, name);
        this.countVotes = countVotes;
        this.dishes = dishes;
    }

    public int getCountVotes() {
        return countVotes;
    }

    public void setCountVotes(int countVotes) {
        this.countVotes = countVotes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                "countVotes=" + countVotes +
                ", dishes={" + getDishesNames() +
                '}';
    }

    private String getDishesNames(){
        StringBuilder dishesNames = new StringBuilder(" ");
        for (Dish dish : dishes) {
            dishesNames = dishesNames.append(dish.getName()).append(' ');
        }
        return dishesNames.toString();
    }
}
