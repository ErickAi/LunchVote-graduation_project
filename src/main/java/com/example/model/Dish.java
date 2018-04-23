package com.example.model;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Dish.ALL, query = "SELECT d FROM Dish d"),
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id"),
})
@Entity
@Table(name = "dishes")
public class Dish extends AbstractNamedEntity {
    public static final String ALL = "Dish.getAll";
    public static final String DELETE = "Dish.delete";

    @Column(name = "price", nullable = false)
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
