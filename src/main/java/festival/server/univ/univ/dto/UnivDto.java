package festival.server.univ.univ.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
    private String homepage;

    @Builder
    public UnivDto(String univId, String stYear, String univCategory, String univName, String univStatus, String branch, String cityDo, String cityGu, String foundation, String dayNight, String address, String homepage) {
        this.univId = univId;
        this.stYear = stYear;
        this.univCategory = univCategory;
        this.univName = univName;
        this.univStatus = univStatus;
        this.branch = branch;
        this.cityDo = cityDo;
        this.cityGu = cityGu;
        this.foundation = foundation;
        this.dayNight = dayNight;
        this.address = address;
        this.homepage = homepage;
    }
}
