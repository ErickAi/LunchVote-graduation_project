package com.example.web;

import com.example.AuthorizedUser;
import com.example.domain.Menu;
import com.example.domain.Restaurant;
import com.example.dto.VoteTo;
import com.example.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class VoteRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    public static final String VOTE_URL = "api/votes";

    @Autowired
    private VoteService service;

    @RequestMapping(method = GET)
    public ResponseEntity<Restaurant> current() {
        return service.getForUserAndDate(AuthorizedUser.id(), LocalDate.now())
                .map(vote -> new ResponseEntity<>(vote.getMenu().getRestaurant(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = POST)
    public ResponseEntity<Restaurant> vote(@PathVariable("id") Menu menu) {
        LocalDate today = LocalDate.now();
        if (menu == null || menu.getDate().isBefore(today)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        VoteTo voteTo = service.createOrUpdate(new VoteTo(AuthorizedUser.id(), menu));

        return new ResponseEntity<>(voteTo.getMenu().getRestaurant(),
                voteTo.isUpdated() ? HttpStatus.CREATED : (voteTo.isExpired() ? HttpStatus.CONFLICT : HttpStatus.OK));
    }
}
