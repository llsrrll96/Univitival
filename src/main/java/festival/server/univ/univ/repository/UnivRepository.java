package festival.server.univ.univ.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public interface UnivRepository extends JpaRepository<UnivEntity, String> {

    @Query("select MAX(univId) from UnivEntity")
    Optional<String> findMaxKey();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO appdb.univ " +
            "(univ_id, st_year, univ_category, univ_name, univ_status, " +
            "branch, city_do, city_gu, foundation, day_night," +
            "address, homepage, edit_dt)" +
            "VALUES " +
            "(:#{#univ.univId}, :#{#univ.stYear}, :#{#univ.univCategory}, :#{#univ.univName}, :#{#univ.univStatus}, " +
            ":#{#univ.branch}, :#{#univ.cityDo}, :#{#univ.cityGu}, :#{#univ.foundation}, :#{#univ.dayNight}, " +
            ":#{#univ.address}, :#{#univ.homepage}, now()) " +
            "on duplicate key update " +
            "st_year = :#{#univ.stYear} " +
            ",univ_category = :#{#univ.univCategory} " +
            ",univ_name = :#{#univ.univName} " +
            ",univ_status = :#{#univ.univStatus} " +
            ",branch = :#{#univ.branch} " +
            ",city_do = :#{#univ.cityDo} " +
            ",city_gu = :#{#univ.cityGu} " +
            ",foundation = :#{#univ.foundation} " +
            ",day_night = :#{#univ.dayNight} " +
            ",address = :#{#univ.address} " +
            ",homepage = :#{#univ.homepage}" +
            ",edit_dt = now()", nativeQuery = true )
    int upsertUniv(@Param("univ") UnivEntity univ) throws IOException, SQLException;
}
