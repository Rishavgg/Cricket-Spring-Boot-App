package com.crick.app.services;

import com.crick.app.entities.Match;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface MatchService {

    // live matches
    List<Match> getLiveMatches();

    // all matches
    List<Match> getAllMatches();

    // get points table
    List<Map<String, String>> getPointsTable();

}
