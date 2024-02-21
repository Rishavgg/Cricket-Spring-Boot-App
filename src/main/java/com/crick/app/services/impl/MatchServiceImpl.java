package com.crick.app.services.impl;

import com.crick.app.entities.Match;
import com.crick.app.repositories.MatchRepository;
import com.crick.app.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

//@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getLiveMatches() {
        
    }

    @Override
    public List<Match> getAllMatches() {
        return this.matchRepository.findAll();
    }

    @Override
    public List<Map<String, String>> getPointsTable() {
        return null;
    }
}
