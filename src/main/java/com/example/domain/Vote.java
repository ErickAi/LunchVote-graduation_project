package com.example.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "vote_date"}, name = "unique_vote")})
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(name = "vote_date", nullable = false)
    private LocalDate date;

    public Vote() {
    }

    public Vote(Vote vote) {
        this(vote.getId(), vote.getUser(), vote.getMenu());
    }

    public Vote(User user, Menu menu) {
        this(null, user, menu);
    }

    public Vote(Integer id, User user, Menu menu) {
        this.id = id;
        this.user = user;
        this.menu = menu;
        this.date = menu.getDate();
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public void setId(Integer id) {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        return id != null ? id.equals(vote.id) : vote.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
