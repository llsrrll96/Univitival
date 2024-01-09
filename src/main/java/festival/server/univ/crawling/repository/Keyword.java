package festival.server.univ.crawling.repository;

import festival.server.univ.univ.repository.UnivEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long keywordId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "univ_id")
//    private UnivEntity univ;
    @Column(length = 100)
    private String word;
    private double rate;
}
