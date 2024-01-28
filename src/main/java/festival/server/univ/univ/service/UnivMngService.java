package festival.server.univ.univ.service;

import festival.server.univ.crawling.dto.RequestSearchParam;
import festival.server.univ.crawling.repository.UnivSite;
import festival.server.univ.crawling.repository.UnivSiteRepository;
import festival.server.univ.univ.dto.UnivDto;
import festival.server.univ.univ.repository.UnivEntity;
import festival.server.univ.univ.repository.UnivRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnivMngService {

    final UnivRepository univRepository;
    final UnivSiteRepository univSiteRepository;

    public void uploadUnivInfo(List<List<Object>> dataList) throws SQLException, IOException {
        for (int i = 5; i<dataList.size(); i++ ) {
            List<Object> row = dataList.get(i);
            if (row.get(1).toString().equals("대학교")) {

                // makeKey: u_0001
                // 해당아이디 있으면 지우고 다시 채우기
                UnivDto univDto = new UnivDto();
                univDto.setUnivName(row.get(3).toString());
                univDto.setCityGu(row.get(7).toString());

                // 없으면 makeKey
                UnivEntity univ = findUnivByDto(univDto)
                        .orElseGet(()->new UnivEntity(makeKey()));

                univ.setUnivEntityByDto(
                    UnivDto.builder()
                        .univId(univ.getUnivId())
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
                        .adrNum(row.get(11).toString())
                        .homepage(row.get(14).toString())
                        .build()
                );

                saveUniv(univ);
            }
        }
    }

    public void saveUniv(UnivEntity univ) {
        univRepository.save(univ);
    }

    public Optional<UnivEntity> findUnivByDto(UnivDto univ) throws SQLException, IOException {
        return univRepository.findByNameAndCityGu(univ.getUnivName(), univ.getCityGu());
    }

    public Optional<UnivEntity> findUnivByParam(RequestSearchParam param) throws SQLException, IOException {
        return univRepository.findUnivByParam(param);
    }

    private String makeKey() {
        String maxId = univRepository.findMaxKey().orElse("u_0000");
        String newKey = String.valueOf(Integer.parseInt(maxId.substring(2))+ 1);
        newKey = "u_"+StringUtils.leftPad(newKey,4,"0");
        return newKey;
    }

    public List<UnivSite> searchUnivUrl(RequestSearchParam param) throws SQLException, IOException {
        // 대학교 - 인스타URL 대조
        String univId = findUnivByParam(param).get().getUnivId();
        List<UnivSite> univSites = univSiteRepository.findUnivSitesByUnivId(univId);
        univSites = univSites.stream()
                .filter(univSite -> univSite.getUrlSiteName().equals(param.getSiteName()))
                .collect(Collectors.toList());
        return univSites;
    }
}
