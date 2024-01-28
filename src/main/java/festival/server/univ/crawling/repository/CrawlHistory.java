package festival.server.univ.crawling.repository;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
public class CrawlHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crlhisId;
    private String univId;
    @Column(length = 100)
    private String univName;
    @Column(length = 200)
    private String url;
    private LocalDateTime urlDate; // 게시 날짜
    private LocalDateTime crawlDate; // 크롤링 날짜
    private String adminId;
//    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public CrawlHistory(String univId, String univName, String url, LocalDateTime urlDate, LocalDateTime crawlDate, String adminId, String content) {
        this.univId = univId;
        this.univName = univName;
        this.url = url;
        this.urlDate = urlDate;
        this.crawlDate = crawlDate;
        this.adminId = adminId;
        this.content = content;
    }
}
