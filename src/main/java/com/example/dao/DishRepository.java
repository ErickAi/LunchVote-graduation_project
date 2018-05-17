package com.example.dao;

import com.example.domain.Dish;
import com.example.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DishRepository  extends JpaRepository<Dish, Integer> {
    @Transactional(readOnly = true)
    @Query("SELECT d FROM Dish d WHERE d.menu.date=:date")
    List<Dish> findByDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @Transactional(readOnly = true)
    @Query("SELECT d FROM Dish d WHERE d.menu=:menu")
    List<Dish> findByMenu(@Param("menu") Menu menu);

    @Override
    Dish save(Dish dish);

    @Override
    void deleteById(Integer id);
}
