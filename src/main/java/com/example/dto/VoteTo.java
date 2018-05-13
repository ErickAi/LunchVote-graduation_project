package com.example.dto;

import com.example.domain.Menu;
import com.example.domain.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class VoteTo extends BaseTo {
    private static final LocalTime EXPIRED_TIME = LocalTime.parse("11:00");

    private int userId;

    private Menu menu;

    private LocalDate date;

    private LocalDateTime currentTime;

    private boolean isExpired;

    private boolean updated;


    public VoteTo() {
    }

    public VoteTo(Vote vote, boolean updated) {
        this(vote.getId(), vote.getUser().getId(), vote.getMenu(), updated);
    }

    public VoteTo(int userId, Menu menu, boolean updated) {
        this(null, userId, menu, updated);
    }

    public VoteTo(Integer id, int userId, Menu menu, boolean updated) {
        super(id);
        this.userId = userId;
        this.menu = menu;
        this.date = menu.getDate();
        currentTime = LocalDateTime.now();
        this.isExpired = currentTime.isAfter(date.atTime(EXPIRED_TIME));
        this.updated = updated;
    }

    public boolean isNew() {
        return (this.id == null);
    }


    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate voteDate) {
        this.date = voteDate;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "id=" + id +
                ", userId=" + userId +
                ", menu=" + menu +
                ", date=" + date +
                ", currentTime=" + currentTime +
                ", isExpired=" + isExpired +
                ", updated=" + updated +
                "}";
    }
}
