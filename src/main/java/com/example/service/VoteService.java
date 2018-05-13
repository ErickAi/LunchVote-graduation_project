package com.example.service;

import com.example.dao.UserRepository;
import com.example.dao.VoteRepository;
import com.example.domain.Menu;
import com.example.domain.User;
import com.example.domain.Vote;
import com.example.dto.VoteTo;
import com.example.util.VoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
@Service("voteService")
public class VoteService {

    @Autowired
    VoteRepository voteRepository;
    @Autowired
    UserRepository userRepository;

    public Optional<Vote> getForUserAndDate(int userId, LocalDate date) {
        return voteRepository.getForUserAndDate(userId, date);
    }

    @Transactional
    public VoteTo createOrUpdate(int userId, final Menu menu) {

        VoteTo voteTo = new VoteTo(userId, menu, false);
        Vote vote;
        if (voteTo.isExpired()) {
            voteTo = voteRepository.getForUserAndDate(userId, menu.getDate())
                    .map((Vote v) -> new VoteTo(v, false))
                    .orElseGet(() -> new VoteTo(userId, menu, true));
        } else {
            voteTo = voteRepository.getForUserAndDate(userId, menu.getDate())
                    .map((Vote v) -> new VoteTo(v.getId(),userId, menu, true))
                    .orElseGet(() -> new VoteTo(userId, menu, true));
        }

        if (voteTo.isUpdated()) {
            User user = userRepository.getOne(userId);
            vote = VoteUtil.prepareToSave(voteTo, user);
            vote = voteRepository.save(vote);
            voteTo.setId(vote.getId());
        }
        return voteTo;
    }

    @Transactional
    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    @Transactional
    public void delete(Vote vote) {
        voteRepository.delete(vote);
    }

    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

}
