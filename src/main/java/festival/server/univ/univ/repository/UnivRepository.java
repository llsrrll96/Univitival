package festival.server.univ.univ.repository;


import festival.server.univ.crawling.dto.RequestSearchParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface UnivRepository extends JpaRepository<UnivEntity, String> {

    @Query("select MAX(univId) from UnivEntity")
    Optional<String> findMaxKey();
    @Query("SELECT u FROM UnivEntity u where u.univName = :name and u.cityGu = :city")
    Optional<UnivEntity> findByNameAndCityGu(@Param("name") String univName, @Param("city") String cityGu) throws IOException, SQLException;
    @Query("SELECT u FROM UnivEntity u where u.univName = :#{#param.univName} and u.cityGu = :#{#param.cityGu}")
    Optional<UnivEntity> findUnivByParam(@Param("param") RequestSearchParam param);
}
