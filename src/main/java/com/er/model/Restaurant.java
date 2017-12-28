package com.er.model;

import java.util.Arrays;
import java.util.List;

public class Restaurant extends AbstractNamedEntity{
    private int countVotes;
    private Menu menu;

    public Restaurant() {
    }

    public Restaurant(String name) {
        super(name);
    }

    public Restaurant(String name, Dish... dishes) {
        this(name, Arrays.asList(dishes));
    }
    public Restaurant(String name, List<Dish> dishes) {
        super(name);
        menu = new Menu(dishes);
    }

    public Restaurant(int id, String name) {
        super(id, name);
    }

    public Restaurant(int id, String name, int countVotes, Menu menu) {
        super(id, name);
        this.countVotes = countVotes;
        this.menu = menu;
    }

    public int getCountVotes() {
        return countVotes;
    }

    public void setCountVotes(int countVotes) {
        this.countVotes = countVotes;
    }


/*
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
*/
}
