package com.example.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = Vote.ALL, query = "SELECT v FROM Vote v"),
        @NamedQuery(name = Vote.ALL_FOR_RESTAURANT, query = "SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId"),
        @NamedQuery(name = Vote.ALL_BY_USER, query = "SELECT v FROM Vote v WHERE v.user.id=:userId")
})
@Entity
@Table(name = "votes")
public class Vote extends AbstractBaseEntity {
    public static final String ALL = "Vote.getAll";
    public static final String ALL_FOR_RESTAURANT = "Vote.getAllForRestaurant";
    public static final String ALL_BY_USER = "Vote.getAllByUser";

    @Column(name = "vote_time", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime voteTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public Vote() {
        this.voteTime = LocalDateTime.now();
    }

    public Vote(Vote vote) {
        this.id = vote.id;
        this.voteTime = vote.voteTime;
        this.user = vote.user;
        this.restaurant = vote.restaurant;
    }

    public Vote(Integer id, LocalDateTime voteTime, User user, Restaurant restaurant) {
        this.id = id;
        this.voteTime = voteTime;
        this.user = user;
        this.restaurant = restaurant;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {

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
