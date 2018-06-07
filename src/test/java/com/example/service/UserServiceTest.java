package com.example.service;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.util.exception.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static com.example.data.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void create() throws Exception {
        User user = new User(null, "NewUser", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(user);
        assertMatch(service.getAll(), ADMIN, USER, created);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(USER_ID);
        assertMatch(user, USER);
    }

    @Test
    public void getAll() throws Exception {
        Collection<User> all = service.getAll();
        assertMatch(Arrays.asList(ADMIN, USER), all);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test
    public void getByEmail() throws Exception {
        User user = service.getByEmail("user@yandex.ru");
        assertMatch(USER, user);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        service.update(updated);
        assertMatch(updated, service.get(USER_ID));
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailSave() throws Exception {
//        service.save(new User(null, "Duplicate", "user@yandex.ru", PasswordUtil.encode("newPass"), Role.ROLE_USER));
        service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", false, new Date(), Collections.singleton(Role.ROLE_USER)));
    }
}