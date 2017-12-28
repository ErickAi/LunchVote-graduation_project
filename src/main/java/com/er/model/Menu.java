package com.er.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Menu extends AbstractEntity {

    private LocalDateTime dateTime;
    private List<Dish> menu = new CopyOnWriteArrayList<>();
    private Integer votes;

    public Menu() {
    }

    public Menu(List<Dish> menu) {
        this(null, LocalDateTime.now(), menu);
    }

    public Menu(Integer id, LocalDateTime dateTime, List<Dish> menu) {

        super(id);
        this.dateTime = dateTime;
        this.menu = menu;
    }

    public Menu(int id, LocalDateTime dateTime, List<Dish> menu, Integer votes) {
        super(id);
        this.dateTime = dateTime;
        this.menu = menu;
        this.votes = votes;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
