package festival.server.univ.univ.dto;

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
}
