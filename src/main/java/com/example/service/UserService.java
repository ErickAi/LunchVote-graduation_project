package com.example.service;


import com.example.domain.User;
import com.example.util.exception.NotFoundException;

import java.util.List;

public interface UserService {

    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    void update(User user);

    List<User> getAll();

    User getByEmail(String email) throws NotFoundException;
}