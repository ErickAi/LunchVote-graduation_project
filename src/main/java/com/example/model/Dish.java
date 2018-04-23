package com.example.model;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

public class Dish extends AbstractNamedEntity {

    private Integer price;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "dishes")
    private List<Menu> menu;

    public Dish() {
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getPrice());
    }

    public Dish(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                "price=" + price +
                '}';
    }
}
