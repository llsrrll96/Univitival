package festival.server.univ;

import festival.server.univ.crawling.dto.UnivSiteDto;
import festival.server.univ.crawling.repository.UnivSite;
import festival.server.univ.crawling.service.UnivSiteService;
import festival.server.univ.univ.dto.UnivDto;
import festival.server.univ.univ.repository.UnivEntity;
import festival.server.univ.univ.service.UnivMngService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UnivApplicationTests {

	@Autowired
	UnivMngService univMngService;
	@Autowired
	UnivSiteService univSiteService;

	@Test
	void 대학교조회() throws SQLException, IOException {
		UnivDto dto = new UnivDto();
		dto.setUnivName("가야대학교");
		dto.setCityGu("경남 김해시");
		UnivEntity univ = univMngService.findUnivByDto(dto).get();
		assertEquals(dto.getUnivName(), univ.getUnivName());
	}

	@Test
	void 대학사이트저장() throws SQLException, IOException {

		// given
		UnivDto univDto = new UnivDto();
		univDto.setUnivName("가야대학교");
		univDto.setCityGu("경남 김해시");

		// when
		UnivEntity univEntity = univMngService.findUnivByDto(univDto).get();
		String url = "https://www.instagram.com/donggukstuco/";
		String urlSiteName = "instagram";
		UnivSite univSite = new UnivSite().builder()
				.url(url)
				.urlSiteName(urlSiteName)
				.univ(univEntity)
				.urlDate(LocalDateTime.now())
				.build();

		// then
		UnivSite savedUnivSite = univSiteService.saveUnivSite(univSite);


		// 검증
		assertEquals(url, savedUnivSite.getUrl());
	}
}
