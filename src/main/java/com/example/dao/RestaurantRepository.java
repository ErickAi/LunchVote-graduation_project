package com.example.dao;

import com.example.domain.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Override
    Restaurant save(Restaurant restaurant);

    @Override
    List<Restaurant> findAll(Sort sort);

    @Override
    Optional<Restaurant> findById(Integer id);

    @Override
    void deleteById(Integer id);

    Restaurant getByName(String name);

    @Query("SELECT r FROM Restaurant r WHERE r.name LIKE CONCAT('%',:name,'%')")
    List<Restaurant> findByName(@Param("name") String name);
}
