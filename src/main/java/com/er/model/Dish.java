package com.er.model;

public class Dish extends AbstractNamedEntity {
    private Integer price;

    public Dish() {
    }

    public Dish(String name, Integer price) {
        super(name);
        this.price = price;
    }

    public Dish(int id, String name, Integer price) {
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
