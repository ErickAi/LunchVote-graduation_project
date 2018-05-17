package com.example.service;

import com.example.dao.UserRepository;
import com.example.dao.VoteRepository;
import com.example.domain.User;
import com.example.domain.Vote;
import com.example.dto.VoteTo;
import com.example.util.VoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public VoteTo createOrUpdate(VoteTo voteTo) {

        if (voteTo.isExpired()) {
            VoteTo finalVoteTo = voteTo;
            voteTo = voteRepository.getForUserAndDate(voteTo.getUserId(),voteTo.getDate())
                    .map(VoteTo::new)
                    .orElseGet(() -> VoteUtil.setIdAndUpdated(finalVoteTo, null, true));
        } else {
            VoteTo finalVoteTo = voteTo;
            voteTo = voteRepository.getForUserAndDate(voteTo.getUserId(), voteTo.getDate())
                    .map((Vote v) -> VoteUtil.setIdAndUpdated(finalVoteTo, v.getId(), true))
                    .orElseGet(() -> VoteUtil.setIdAndUpdated(finalVoteTo, null, true));
        }

        if (voteTo.isUpdated()) {
            User user = userRepository.getOne(voteTo.getUserId());
            Vote vote = voteRepository.save(VoteUtil.prepareToSave(voteTo, user));
            voteTo.setId(vote.getId());
        }
        return voteTo;
    }

    @Transactional
    public void delete(Vote vote) {
        voteRepository.delete(vote);
    }
}
