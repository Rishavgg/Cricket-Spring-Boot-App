package com.crick.app.controllers;

import com.crick.app.entities.Match;
import com.crick.app.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    MatchService matchService;

    // get live matches
    @GetMapping("/live")
    public ResponseEntity<List<Match>> getLiveMatches() {
        return new ResponseEntity<>(this.matchService.getLiveMatches(), HttpStatus.OK);
    }

    // get all matches
    @GetMapping("/allMatches")
    public ResponseEntity<List<Match>> getAllMatches() {
        return new ResponseEntity<>(this.matchService.getAllMatches(), HttpStatus.OK);
    }

}
