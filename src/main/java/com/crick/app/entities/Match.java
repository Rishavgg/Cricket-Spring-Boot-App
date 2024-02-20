package com.crick.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cric_match")
public class Match {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long matchId;
    private String battingTeam;
    private String  battingTeamScore;
    private String bowlTeam;
    private String bowlTeamScore;
    private String liveText;
    private String matchLink;
    private String matchNumberVenue;

    @Enumerated
    private MatchStatus matchStatus;
    private String teamHeading;
    private String textComplete;

    @Autowired
    private Date date;

    // set the match status according to textComplete
    public void setMatchStatus(String textComplete) {
        this.matchStatus = textComplete.isBlank() ? MatchStatus.LIVE : MatchStatus.ENDED;
    }

}
