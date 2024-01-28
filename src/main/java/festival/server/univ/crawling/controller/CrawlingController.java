package festival.server.univ.crawling.controller;

import festival.server.univ.common.ApiResponseDTO;
import festival.server.univ.common.DTOHandler;
import festival.server.univ.crawling.dto.RequestSearchParam;
import festival.server.univ.crawling.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CrawlingController {

    final CrawlingService crawlingService;
    final DTOHandler dtoHandler;
    /**
     *     "cityGu" : "경남 김해시",
     *     "univName" : "가야대학교",
     *     "postDate" : "20230101",
     *     "siteName" : "instagram"
     */
    @PostMapping("/crawl/boards")
    public ResponseEntity<ApiResponseDTO<Integer>> crawling(@RequestBody RequestSearchParam param) {
        log.info("{}",param);
        int count = crawlingService.processScraping(param);
        ApiResponseDTO<Integer> apiResponseDTO = new ApiResponseDTO<>();
        apiResponseDTO = dtoHandler.buildSuccessResponse(apiResponseDTO, count);
        return ResponseEntity.ok(apiResponseDTO);
    }
}
