package festival.server.univ.crawling.dto;

import festival.server.univ.univ.dto.UnivDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UnivSiteDto {
    private long univSiteId;
    private UnivDto univDto;
    private String url;
    private String urlSiteName;
    private String content;
    private LocalDateTime urlDate; // 게시된 url date
}
