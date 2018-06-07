package com.example.web;

import com.example.AuthorizedUser;
import com.example.dao.MenuRepository;
import com.example.domain.Menu;
import com.example.domain.Restaurant;
import com.example.dto.VoteTo;
import com.example.service.VoteService;
import com.example.util.exception.DateExpiredException;
import com.example.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = VoteRestController.VOTE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {

    public static final String VOTE_URL = "/api/vote";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping
    public ResponseEntity<Restaurant> current() {
        log.info("current");
        return service.getForUserAndDate(AuthorizedUser.id(), LocalDate.now())
                .map(vote -> new ResponseEntity<>(vote.getMenu().getRestaurant(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/for-menu/{menuId}")
    public ResponseEntity<Restaurant> vote(@PathVariable("menuId") int menuId) {
        log.info("vote for menu ({})", menuId);
        Menu menu  = menuRepository.findById(menuId).orElseThrow(()-> new NotFoundException("Not found menu with id= " + menuId));

        if (menu.getDate().isBefore(LocalDate.now())) {
            throw new DateExpiredException("Invalid date. It is impossible to vote for the menu with id= " + menuId);

        }

        VoteTo voteTo = service.createOrUpdate(new VoteTo(AuthorizedUser.id(), menu));

        return new ResponseEntity<>(voteTo.getMenu().getRestaurant(),
        voteTo.isUpdated() ? HttpStatus.OK : HttpStatus.NOT_MODIFIED);
    }
}
