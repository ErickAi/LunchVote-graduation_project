package com.example.dao;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Override
    User save(User user);

    @Override
    List<User> findAll();

    @Override
    User getOne(Integer id);

    @Override
    void deleteById(Integer id);

    Optional<User> getByEmail(String email);

    @Override
    boolean existsById(Integer id);
}
