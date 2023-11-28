package festival.server.univ.crawling.controller;

import festival.server.univ.crawling.dto.RequestSearchParam;
import festival.server.univ.crawling.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CrawlingController {

    final CrawlingService crawlingService;

    @GetMapping("/crawling")
    public ResponseEntity<Integer> crawling(@RequestBody RequestSearchParam param) {

        return ResponseEntity.ok(crawlingService.processScraping(param));
    }
}
