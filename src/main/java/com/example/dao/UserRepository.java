package com.example.dao;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Override
    User getOne(Integer integer);

    @Override
    User save(User user);

    @Override
    boolean existsById(Integer id);

    @Override
    void deleteById(Integer id);

    User getByEmail(String email);
}
