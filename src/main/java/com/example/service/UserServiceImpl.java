package com.example.service;

import com.example.dao.GenericRepository;
import com.example.domain.User;
import com.example.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.example.util.ValidationUtil.checkNotFound;
import static com.example.util.ValidationUtil.checkNotFoundWithId;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final GenericRepository<User, Integer> repository;

    @Autowired
    public UserServiceImpl(GenericRepository<User, Integer> repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.existsById(id),id);
        repository.deleteById(id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        checkNotFoundWithId(repository.existsById(id), id);
        return repository.findById(id).orElse(null);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.getId());
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }
}