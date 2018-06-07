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

    /**
     * Returns {@link Vote} as {@link VoteTo}
     * <p>
     * if {@link Vote} exists and the voting time has expired, the unchanged vote returns
     * if {@link Vote} exists and the voting time has not expired, the updated vote returns
     * if {@link Vote} does not exists, new vote returns
     */

    @Transactional
    public VoteTo createOrUpdate(VoteTo voteTo) {
        VoteTo finalVoteTo = voteTo;

        if (voteTo.isExpired()) {
            voteTo = voteRepository.getForUserAndDate(voteTo.getUserId(), voteTo.getDate())
                    .map(VoteTo::new)                                                                   //vote exists, update forbidden
                    .orElseGet(() -> VoteUtil.setIdAndUpdated(finalVoteTo, null, true));     //vote new, create allowed
        } else {
            voteTo = voteRepository.getForUserAndDate(voteTo.getUserId(), voteTo.getDate())
                    .map((Vote v) -> VoteUtil.setIdAndUpdated(finalVoteTo, v.getId(), true))    //vote exists, update allowed
                    .orElseGet(() -> VoteUtil.setIdAndUpdated(finalVoteTo, null, true));     //vote new, create allowed
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
