package com.crick.app.services.impl;

import com.crick.app.entities.Match;
import com.crick.app.repositories.MatchRepository;
import com.crick.app.services.MatchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

//@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<Match> getLiveMatches() {
        List<Match> matches = new ArrayList<>();
        try {
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-1st.cb-tms-itm");
            for (Element match : liveScoreElements) {
                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
                String teamHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberVenue = match.select("span").text();
                Elements matchBatTeamInfo = match.select(".cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select(".cb-hmscg-tm-nm").text();
                String batTeamScore = matchBatTeamInfo.select(".cb-ovr-flo").text();
                Elements matchBowlTeamInfo = match.select(".cb-hmscg-bwl-txt ");
                String bowlingTeam = matchBowlTeamInfo.select(".cb-hmscg-tm-nm").text();
                String bowlingTeamScore = matchBowlTeamInfo.select(".cb-ovr-flo").text();
                String textLive = match.select(".cb-text-live").text();
                // getting Match link
                String matchLink = match.select(".cb-lv-scrs-well.cb-lv-scrs-well-live").select("href").text();

                Match matchObj = Match.builder()
                        .matchNumberVenue("matchNumberVenue")
                        .teamHeading("teamHeading")
                        .battingTeam("battingTeam")
                        .battingTeamScore("batTeamScore")
                        .bowlTeam("bowlingTeam")
                        .bowlTeamScore("batTeamScore")
                        .liveText("textLive")
                        .matchLink("matchLink")
                        .build();
                matches.add(matchObj);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
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
