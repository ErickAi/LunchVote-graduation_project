package com.example.web;

import com.example.dao.DishRepository;
import com.example.domain.Dish;
import com.example.service.MenuService;
import com.example.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.example.util.ValidationUtil.NOT_FOUND_WITH;
import static com.example.util.ValidationUtil.assureIdConsistent;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Transactional(readOnly = true)
@RequestMapping(value = DishRestController.DISH_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {

    public static final String DISH_URL = "/api/dishes";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final Sort SORT_DATE = new Sort(Sort.Direction.DESC, "menu.date");

    @Autowired
    private DishRepository repository;

    @Autowired
    MenuService menuService;

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish) {
        dish.setMenu(menuService.createOrUpdate(dish.getMenu()));
        assureIdConsistent(dish, repository.getByNameAndMenu(dish.getName(), dish.getMenu()));
        Dish created = repository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DISH_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping
    public List<Dish> getAll() {
        log.info("getAll");
        return repository.findAll(SORT_DATE);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Dish> get(@PathVariable("id") int id) {
        log.info("get ({})", id);
        return repository.findById(id)
                .map(dish -> new ResponseEntity<>(dish, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id) {
        log.info("update ({}) with id=({})", dish, id);
        dish.setMenu(menuService.createOrUpdate(dish.getMenu()));
        assureIdConsistent(dish, id);
        repository.save(dish);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("delete ({})", id);
        repository.deleteById(id);
    }

    @GetMapping(value = "/for-date")
    public List<Dish> getAllForDate(@RequestParam(value = "date", required = false) LocalDate date) {
        log.info("getAllForDate ({})", date);
        return repository.findByDate(date)
                .orElseGet(()-> repository.findByDate(LocalDate.now())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_WITH + date)));
    }

    @RequestMapping(value = "/for-menu/{menuId}", method = GET)
    public List<Dish> getAllForMenu(@PathVariable("menuId") int menuId) {
        log.info("getAllForMenu ({})", menuId);
        return repository.findByMenu(menuId)
                .orElseThrow(() -> new NotFoundException("Not found dishes for menu " + menuId));
    }
}
