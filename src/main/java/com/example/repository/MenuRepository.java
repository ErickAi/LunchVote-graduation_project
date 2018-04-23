package com.example.repository;

import com.example.model.Menu;

import java.time.LocalDateTime;
import java.util.List;

public interface MenuRepository {
    // null if updated meal do not belong to userId
    Menu save(Menu meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Menu get(int id, int userId);

    // ORDERED dateTime desc
    List<Menu> getAll(int userId);

    // ORDERED dateTime desc
    List<Menu> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}

