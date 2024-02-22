package com.crick.app.services.impl;

import com.crick.app.entities.Match;
import com.crick.app.entities.MatchStatus;
import com.crick.app.repositories.MatchRepository;
import com.crick.app.services.MatchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getLiveMatches() {
        List<Match> matches = new ArrayList<>();
        try {
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
            System.out.println("--------------------------------------------------------------");

            for (Element match : liveScoreElements) {

                String teamHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberVenue = match.select("span").text();
                Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String batTeamScore = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                Elements matchBowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
                String bowlingTeam = matchBowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String bowlingTeamScore = matchBowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                String textLive = match.select("div.cb-text-live").text();
                String textComplete = match.select("div.cb-text-complete").text();
                // getting Match link
                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                Match matchObj = Match.builder()
                        .matchNumberVenue(matchNumberVenue)
                        .teamHeading(teamHeading)
                        .battingTeam(battingTeam)
                        .battingTeamScore(batTeamScore)
                        .bowlTeam(bowlingTeam)
                        .bowlTeamScore(bowlingTeamScore)
                        .liveText(textLive)
                        .matchLink(matchLink)
                        .textComplete(textComplete)
                        .matchStatus(setMatchStatusFunc(textComplete))
                        .date(new Date())
                        .build();

                matches.add(matchObj);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }
    public MatchStatus setMatchStatusFunc(String textComplete) {
        return textComplete.isBlank() ? MatchStatus.LIVE : MatchStatus.ENDED;
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
