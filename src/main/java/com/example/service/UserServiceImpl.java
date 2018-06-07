package com.example.service;

import com.example.AuthorizedUser;
import com.example.dao.UserRepository;
import com.example.domain.User;
import com.example.dto.UserTo;
import com.example.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

import static com.example.util.UserUtil.prepareToSave;
import static com.example.util.UserUtil.updateFromTo;
import static com.example.util.ValidationUtil.*;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;

}
    @Transactional
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user, passwordEncoder));
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User get(int id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND_WITH_ID + id));
    }
    @Transactional
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFound(repository.save(prepareToSave(user, passwordEncoder)), NOT_FOUND_WITH_ID + user.getId());
    }

    @Transactional
    @Override
    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        repository.save(prepareToSave(user, passwordEncoder));
    }

    @Transactional
    public void delete(int id) throws NotFoundException {
        checkNotFound(repository.existsById(id), NOT_FOUND_WITH_ID + id);
        repository.deleteById(id);
    }

    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return repository.getByEmail(email).orElseThrow(() -> new NotFoundException(NOT_FOUND_WITH + "email " + email));
    }

    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repository.getByEmail(email.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " is not found"));

        return new AuthorizedUser(u);
    }
}