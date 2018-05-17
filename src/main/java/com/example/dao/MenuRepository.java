package com.example.dao;

import com.example.domain.Menu;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Override
    Optional<Menu> findById(Integer id);

    @Override
    List<Menu> findAll();

    @Override
    List<Menu> findAll(Sort sort);

    @Override
    Menu save(Menu menu);

    @Override
    void deleteById(Integer id);

    @Query("SELECT m FROM Menu m WHERE m.date=:date")
    Optional<List<Menu>> getAllForDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
    Optional<List<Menu>> getAllForRestaurant(@Param("restaurantId") int restaurantId);
}
