package com.example.model;

import com.example.HasId;

import java.time.LocalDateTime;

public class Vote implements HasId {

    private Integer id;

    private LocalDateTime voteTime;

    private User user;

    private Menu menu;

    public Vote() {
        this.voteTime = LocalDateTime.now();
    }

    public Vote(Vote vote) {
        this.id = vote.id;
        this.voteTime = vote.voteTime;
        this.user = vote.user;
        this.menu = vote.menu;
    }

    public Vote(int id, LocalDateTime dateTime, User user, Menu menu) {
        this.id = id;
        this.voteTime = dateTime;
        this.user = user;
        this.menu = menu;
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
