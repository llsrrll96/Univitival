package festival.server.univ.crawling.repository;

import festival.server.univ.univ.repository.UnivEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class UnivSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long univSiteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "univ_id")
    private UnivEntity univ;

    @Column(length = 200)
    private String url;
    @Column(length = 100)
    private String urlSiteName;
    private String content;
    private LocalDateTime urlDate; // 게시된 url date

    @Builder
    public UnivSite(UnivEntity univ, String url, String urlSiteName, String content, LocalDateTime urlDate) {
        this.univ = univ;
        this.url = url;
        this.urlSiteName = urlSiteName;
        this.content = content;
        this.urlDate = urlDate;
    }
}
