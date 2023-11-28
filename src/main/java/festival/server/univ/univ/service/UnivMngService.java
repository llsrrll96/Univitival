package festival.server.univ.univ.service;

import festival.server.univ.univ.dto.UnivDto;
import festival.server.univ.univ.repository.UnivEntity;
import festival.server.univ.univ.repository.UnivRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnivMngService {

    final UnivRepository univRepository;

    public void uploadUnivInfo(List<List<Object>> dataList) throws SQLException, IOException {
        Map<String, Integer> headersMap = new LinkedHashMap<>();
        headersMap.put("stYear", 0);
        headersMap.put("univ_category", 1); // 학제
        headersMap.put("univ_name", 3);
        headersMap.put("univ_status", 4);
        headersMap.put("branch", 5);
        headersMap.put("city_do", 6);
        headersMap.put("city_gu", 7);
        headersMap.put("foundation", 8);
        headersMap.put("day_night", 9);
        headersMap.put("address", 10);
        headersMap.put("homepage", 14);

        for (int i = 5; i<dataList.size(); i++ ) {
            List<Object> row = dataList.get(i);
            if (row.get(1).toString().equals("대학교")) {

                // makeKey: u_0001
                String univId = makeKey();
                UnivEntity univ = new UnivEntity();
                univ.setUnivEntityByDto(
                    UnivDto.builder()
                        .univId(univId)
                        .stYear(row.get(0).toString())
                        .univCategory(row.get(1).toString())
                        .univName(row.get(3).toString())
                        .univStatus(row.get(4).toString())
                        .branch(row.get(5).toString())
                        .cityDo(row.get(6).toString())
                        .cityGu(row.get(7).toString())
                        .foundation(row.get(8).toString())
                        .dayNight(row.get(9).toString())
                        .address(row.get(10).toString())
                        .homepage(row.get(14).toString())
                        .build()
                );

                // upsert
                upsertUniv(univ);
            }
        }
    }

    public void upsertUniv(UnivEntity univ) throws SQLException, IOException {
        univRepository.upsertUniv(univ);
    }

    //u_0001
    private String makeKey() {
        String maxId = univRepository.findMaxKey().orElse("u_0001");
        String newKey = String.valueOf(Integer.parseInt(maxId.substring(2))+ 1);
        newKey = "u_"+StringUtils.leftPad(newKey,4,"0");
        return newKey;
    }


}
