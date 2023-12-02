package festival.server.univ.univ.repository;

import festival.server.univ.univ.dto.UnivDto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "UNIV")
@Getter
@NoArgsConstructor
public class UnivEntity {
    @Id
    @Column(length = 100)
    private String univId;
    @Column(length = 20)
    private String stYear;
    @Column(length = 20)
    private String univCategory;
    @Column(length = 20)
    private String univName;
    @Column(length = 20)
    private String univStatus;
    @Column(length = 20)
    private String branch;
    @Column(length = 20)
    private String cityDo;
    @Column(length = 20)
    private String cityGu;
    @Column(length = 20)
    private String foundation; // 국립 사립
    @Column(length = 20)
    private String dayNight;
    @Column(length = 100)
    private String address;
    @Column(length = 100)
    private String homepage;
    private String adrNum;
    @CreationTimestamp
    private Timestamp editDt;

    public UnivEntity(String univId) {
        this.univId = univId;
    }

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
        adrNum = univDto.getAdrNum();
        homepage = univDto.getHomepage();
    }
}
