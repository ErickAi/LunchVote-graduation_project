package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Restaurant extends AbstractNamedEntity {

    public Restaurant() {
    }
    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "restaurant")
    protected List<Menu> menus = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "menu")
    private List<Vote> votes = new ArrayList<>();

    public Restaurant(Restaurant restaurant) {
        this(restaurant.getId(), restaurant.getName(), restaurant.getDescription(), restaurant.getMenus(), restaurant.getVotes());
    }

    public Restaurant(Integer id, String name, String description, List<Menu> menus, List<Vote> votes) {
        super(id, name);
        this.description = description;
        this.menus = menus;
        this.votes = votes;
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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
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
