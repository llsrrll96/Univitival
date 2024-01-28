package festival.server.univ.crawling.service;

import festival.server.univ.crawling.dto.UnivSiteDto;
import festival.server.univ.crawling.repository.CrawlHistory;
import festival.server.univ.crawling.repository.UnivSite;
import festival.server.univ.crawling.repository.UnivSiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnivSiteService {
    final UnivSiteRepository univSiteRepository;

    public UnivSite saveUnivSite(UnivSite univSite) {
        return univSiteRepository.save(univSite);
    }

    public List<UnivSite> findUnivSitesByUnivId(String univId) {
        return univSiteRepository.findUnivSitesByUnivId(univId);
    }

    public void saveCrawlHistory(List<UnivSiteDto> univSiteDtos) {
        for (UnivSiteDto univSiteDto : univSiteDtos) {
            LocalDateTime urlDate = LocalDateTime.parse(univSiteDto.getUrlDate() + "T00:00:00");
            CrawlHistory crawlHistory = CrawlHistory.builder()
                            .univId(univSiteDto.getUnivId())
                            .univName(univSiteDto.getUrlSiteName())
                            .url(univSiteDto.getUrl())
                            .content(univSiteDto.getContent())
                            .urlDate(urlDate)
                            .crawlDate(LocalDateTime.now())
                            .build();
            univSiteRepository.saveCrawlHistory(crawlHistory);
        }
    }

    public void saveCrawlHistory(CrawlHistory crawlHistory) {
        univSiteRepository.saveCrawlHistory(crawlHistory);
    }
}