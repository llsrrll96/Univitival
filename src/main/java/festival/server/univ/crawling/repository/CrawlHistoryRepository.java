package festival.server.univ.crawling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrawlHistoryRepository extends JpaRepository<CrawlHistory, Long> {
    @Query(
            "select c.url " +
            "from CrawlHistory c " +
            "where c.univId = :univId"
    )
    List<String> findUrlsByUnivId(String univId);
}
