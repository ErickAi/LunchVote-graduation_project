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

    private boolean updated;


    public VoteTo() {
    }

    public VoteTo(Vote vote) {
        this(vote.getId(), vote.getUser().getId(), vote.getMenu());
    }

    public VoteTo(VoteTo voteTo) {
        this(voteTo.getId(), voteTo.getUserId(), voteTo.getMenu());
        this.updated = voteTo.isUpdated();
    }

    public VoteTo(int userId, Menu menu) {
        this(null, userId, menu);
    }

    public VoteTo(Integer id, int userId, Menu menu) {
        super(id);
        this.userId = userId;
        this.menu = menu;
        this.date = menu.getDate();
        currentTime = LocalDateTime.now();
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
        return currentTime.isAfter(date.atTime(EXPIRED_TIME));
    }

    public boolean isUpdated() {
        return updated;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
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
                ", currentTime=" + currentTime +
                ", isExpired=" + isExpired() +
                ", updated=" + updated +
                ", userId=" + userId +
                ", menu=" + menu +
                ", date=" + date +
                "}";
    }
}
