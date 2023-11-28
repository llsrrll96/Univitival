package festival.server.univ.univ.repository;

import festival.server.univ.univ.dto.UnivDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "UNIV")
@Getter
public class UnivEntity {
    @Id
    private String univId;
    private String stYear;
    private String univCategory;
    private String univName;
    private String univStatus;
    private String branch;
    private String cityDo;
    private String cityGu;
    private String foundation; // 국립 사립
    private String dayNight;
    private String address;
    private String homepage;
    @CreationTimestamp
    private Timestamp editDt;

    public void setUnivEntityByDto(UnivDto univDto) {
        univId = univDto.getUnivId();
        stYear = univDto.getStYear();
        univCategory = univDto.getUnivCategory();
        univName = univDto.getUnivName();
        univStatus = univDto.getUnivStatus();
        branch = univDto.getBranch();
        cityDo = univDto.getCityDo();
        cityGu = univDto.getCityGu();
        foundation = univDto.getFoundation();
        dayNight = univDto.getDayNight();
        address = univDto.getAddress();
        homepage = univDto.getHomepage();
    }
}
