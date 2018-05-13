package com.example.domain;

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
    private List<Menu> menus;

    public Dish() {
    }

    public Dish(Dish dish) {
        this(dish.getId(), dish.getName(), dish.getPrice(), dish.getMenu());
    }

    public Dish(Integer id, String name, Integer price, List<Menu> menus) {
        super(id, name);
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Menu> getMenu() {
        return menus;
    }

    public void setMenu(List<Menu> menu) {
        this.menus = menu;
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
