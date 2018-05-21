package com.example.service;

import com.example.domain.User;
import com.example.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);

    User get(int id) throws NotFoundException;

    List<User> getAll();

    void update(User user);

    void delete(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void enable(int id, boolean enabled);

    void evictCache();
}