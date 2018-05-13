package com.example.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    protected List<Menu> menus;

    public Restaurant() {
    }
    public Restaurant(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
        this.menus = new ArrayList<>();
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getDescription(), restaurant.getMenus());
    }

    public Restaurant(Integer id, String name, String description, List<Menu> menus) {
        super(id, name);
        this.description = description;
        this.menus = menus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                "description='" + description + '\'' +
                '}';
    }
}
