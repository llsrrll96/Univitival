package festival.server.univ.crawling.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnivSiteRepository extends JpaRepository<UnivSite,Long> {
    @Query("SELECT s FROM UnivSite s WHERE s.univ.univId = :univId")
    List<UnivSite> findUnivSitesByUnivId(@Param("univId") String univId);


    @Modifying
    @Transactional
    @Query("INSERT INTO CrawlHistory" +
        "(adminId, univId, univName, url, urlDate, crawlDate, content)" +
        "VALUES" +
        "(:#{#crawlHistory.adminId},:#{#crawlHistory.univId}, :#{#crawlHistory.univName}, :#{#crawlHistory.url}, :#{#crawlHistory.urlDate}, :#{#crawlHistory.crawlDate}, :#{#crawlHistory.content})")
    void saveCrawlHistory(@Param("crawlHistory") CrawlHistory crawlHistory);
}
