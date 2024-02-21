package com.crick.app.repositories;

import com.crick.app.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Long, Match> {

    // I want to fetch match with teamHeading
     Optional<Match> findByTeachHeading(String teamHeading);


}
