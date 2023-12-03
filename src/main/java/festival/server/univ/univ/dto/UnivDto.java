package festival.server.univ.univ.dto;

import festival.server.univ.univ.repository.UnivEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnivDto {
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
    private String adrNum;
    private String homepage;


    public UnivDto(UnivEntity univEntity) {
        this.univId = univEntity.getUnivId();
        this.stYear = univEntity.getStYear();
        this.univCategory = univEntity.getUnivCategory();
        this.univName = univEntity.getUnivName();
        this.univStatus = univEntity.getUnivStatus();
        this.branch = univEntity.getBranch();
        this.cityDo = univEntity.getCityDo();
        this.cityGu = univEntity.getCityGu();
        this.foundation = univEntity.getFoundation();
        this.dayNight = univEntity.getDayNight();
        this.address = univEntity.getAddress();
        this.adrNum = univEntity.getAdrNum();
        this.homepage = univEntity.getHomepage();
    }
}
