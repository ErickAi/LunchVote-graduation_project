package com.example.dao;

import com.example.domain.Dish;
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
public interface DishRepository  extends JpaRepository<Dish, Integer> {

    @Override
    Dish save(Dish dish);

    @Override
    List<Dish> findAll(Sort sort);

    @Override
    Optional<Dish> findById(Integer id);

    @Override
    void deleteById(Integer id);

    Dish getByNameAndMenu(String name, Menu menu);

    @Query("SELECT d FROM Dish d WHERE d.menu.date=:date")
    Optional<List<Dish>> findByDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @Query("SELECT d FROM Dish d WHERE d.menu.id=:menuId")
    Optional<List<Dish>> findByMenu(@Param("menuId") int menuId);
}
