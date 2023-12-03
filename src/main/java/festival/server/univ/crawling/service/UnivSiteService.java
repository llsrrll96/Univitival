package festival.server.univ.crawling.service;

import festival.server.univ.crawling.repository.UnivSite;
import festival.server.univ.crawling.repository.UnivSiteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}