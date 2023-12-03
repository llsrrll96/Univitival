package festival.server.univ.crawling.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UnivSiteRepository extends JpaRepository<UnivSite,Long> {
    @Query("SELECT s FROM UnivSite s WHERE s.univ.univId = :univId")
    List<UnivSite> findUnivSitesByUnivId(@Param("univId") String univId);
}
