package com.crick.app.repositories;

import com.crick.app.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    // I want to fetch match with teamHeading
     Optional<Match> findByTeamHeading(String teamHeading);


}
