package com.crick.app.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cric_match")
public class Match {

    @Id
    @SequenceGenerator(
            name = "match_sequence",
            sequenceName = "match_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "match_sequence"
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
    private Date date = new Date();

    public void setMatchStatus() {
        this.matchStatus = textComplete.isBlank() ? MatchStatus.LIVE : MatchStatus.ENDED;
    }

}
