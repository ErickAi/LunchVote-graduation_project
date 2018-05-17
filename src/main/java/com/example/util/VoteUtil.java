package com.example.util;

import com.example.domain.User;
import com.example.domain.Vote;
import com.example.dto.VoteTo;

public class VoteUtil {

    public static Vote prepareToSave(VoteTo voteTo, User user) {
        return new Vote(voteTo.getId(), user, voteTo.getMenu());
    }


    public static VoteTo setIdAndUpdated(VoteTo voteTo, Integer id, boolean updated) {
        voteTo.setId(id);
        voteTo.setUpdated(updated);
        return voteTo;
    }
}
