package com.example.dao;

import com.example.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository  extends JpaRepository<Vote, Integer> {

    @Override
    Vote getOne(Integer id);

    @Override
    boolean existsById(Integer id);

    @Override
    @Transactional
    Vote save(Vote vote);

    @Override
    @Transactional
    void deleteById(Integer id);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:date")
    Optional<Vote> getForUserAndDate(@Param("userId") int userId, @Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

}
