package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "unique_restaurant")})
public class Restaurant extends AbstractNamedEntity {

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "restaurant")
    private List<Menu> menus;

    public Restaurant() {
    }
    public Restaurant(Integer id, String name) {
        super(id, name);
        this.menus = new ArrayList<>();
    }

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getMenus());
    }

    public Restaurant(Integer id, String name, List<Menu> menus) {
        super(id, name);
        this.menus = menus;
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
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
