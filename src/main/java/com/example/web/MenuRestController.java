package com.example.web;

import com.example.dao.MenuRepository;
import com.example.domain.Menu;
import com.example.dto.MenuTo;
import com.example.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.example.util.ValidationUtil.assureIdConsistent;
import static com.example.web.MenuRestController.MENU_URL;

@RestController
@RequestMapping(value = MENU_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {

    public static final String MENU_URL = "/api/menus";
    private final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private MenuService service;

    @Autowired
    private MenuRepository repository;

/*
    @PostMapping(value = RESTAURANT_URL + "/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestBody Menu menu, @PathVariable("restaurantId") int restaurantId) {
        Menu created = service.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(MENU_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
*/

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestBody Menu menu) {
        log.info("create menu " + menu);

        Menu created = service.createOrUpdate(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(MENU_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping
    public List<MenuTo> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(path ="/{id}")
    public MenuTo get(@PathVariable("id") int id) {
        log.info("getOne({})", id);
        return service.get(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Menu menu, @PathVariable("id") int id) {
        log.info("update {} with id={}", menu, id);
        assureIdConsistent(menu, id);
        service.createOrUpdate(menu);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path ="/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete ({})", id);
        repository.deleteById(id);
    }

    @GetMapping(value ="/for-date")
    public List<MenuTo> getAllForDate(
            @RequestParam(value = "date", required = false) LocalDate date) {
        log.info("getAllForDate ({})", date);
        return service.getAllForDate(date);
    }

    @GetMapping(value ="/for-restaurant/{restaurantId}")
    public List<MenuTo> getAllForRestaurant(@PathVariable("restaurantId") int restaurantId) {
        log.info("getAllForRestaurant ({})", restaurantId);
        return service.getAllForRestaurant(restaurantId);
    }
}