package com.example.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "name"}, name = "unique_dish")})
public class Dish extends AbstractNamedEntity {

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public Dish() {
    }

    public Dish(String name, Integer price) {
        this(null, name, price);
    }

    public Dish(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }

    public Dish(Integer id, String name, Integer price, Menu menu) {
        this(id, name, price);
        this.menu = menu;
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getPrice(), dish.getMenu());
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" +
                name +
                ", id=" + id +
                ", price=" + price +
                '}';
    }
}
