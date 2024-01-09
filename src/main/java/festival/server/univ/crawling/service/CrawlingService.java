package festival.server.univ.crawling.service;

import festival.server.univ.common.ApiResponseDTO;
import festival.server.univ.common.DTOHandler;
import festival.server.univ.crawling.dto.RequestSearchParam;
import festival.server.univ.crawling.dto.UnivSiteDto;
import festival.server.univ.univ.service.UnivMngService;
import festival.server.univ.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@Service
public class CrawlingService {
    final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    final String WEB_DRIVER_PATH = "C:\\driver\\chromedriver-win64\\chromedriver159.exe";
    final String instaUrl = "https://instagram.com";

    //로그인 정보 입력
    String username = "";
    String password = "";

    WebDriverWait wait = null;
    WebDriver driver = null;
    private Random random = new Random();

    final UnivMngService univMngService;

    public CrawlingService(UnivMngService univMngService) {
        this.univMngService = univMngService;
    }

    public List<String> processScraping(RequestSearchParam param) {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("headless");

        driver = new ChromeDriver(options);

        List<String> univUrlList;
        try {
//            driver.get(instaUrl);
//            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            // 인스타그램 로그인
//            loginInsta();

            // 대학별 찾기
            // 대학교 url 검색, 여러개 일수도 있다..
            univUrlList = univMngService.searchUnivUrl(param);
            for (String univUrl : univUrlList) {
                driver.get(univUrl);
                // 페이지가 로딩될 때까지 기다리기
                waitForPageLoad(driver,"article");

                /* 게시물 리스트 가져오기 */
                 // 한 계정 첫 페이지의 게시물들 (12개) 링크를 가져옴
                List<String> boardUrls = getBoardLinksByInstaAccount();
                driver.quit();
                return boardUrls;
                // UnivSite table   조회해서 이미 조회한 url은 제외(filter).

//                /* 크롤링된 정보 모으기 */
//                List<UnivSiteDto> univSites = collectUnivSite(driver, boardUrls);

//
//
//                /* 대동제 인지 검증 */
//                boolean isFestival = checkFestival(univSites.get(0));
//                // save festival table
//                /* UnivSite table기록 */
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            driver.close();
        }

        return new ArrayList<>();
    }

    private List<String> getBoardLinksByInstaAccount() {
        String boardClasses = "div._aabd._aa8k._al3l";
        List<WebElement> elements = driver.findElements(By.cssSelector(boardClasses));
        List<String> boardUrls = new ArrayList<>();
        for (WebElement element : elements) {
            String href = element.findElement(By.tagName("a")).getAttribute("href");
//            log.info("12개 url 가져오기: {}",href);
            boardUrls.add(href);
        }
        return boardUrls;
    }

    private boolean checkFestival(UnivSiteDto univSiteDto) {
        // 대학에 대한 키워드 들고옴

        return false;
    }

    /**
     * url 순회하면서 게시물 정보를 UnivSiteDto에 저장
     */
    private List<UnivSiteDto> collectUnivSite(WebDriver driver, List<String> urlList) {
        List<UnivSiteDto> univSites = new ArrayList<>();
        for (String url : urlList) {
            driver.get(url);
            // 페이지가 로딩될 때까지 기다리기
            waitForPageLoad(driver,"article");

            // 내용
            WebElement contentElem = driver.findElement(By.cssSelector("h1._ap3a"));
            String content = contentElem.getText();

            // 시간태그 2번째 것
            List<WebElement> timeElems = driver.findElements(By.tagName("time"));
            String time = timeElems.get(1).getAttribute("datetime");
            time = time.substring(0, time.indexOf("T"));

            UnivSiteDto univSiteDto = new UnivSiteDto();
            univSiteDto.setUrl(url);
            univSiteDto.setContent(content);
            univSiteDto.setUrlDate(time);
            log.debug("############ univSiteDto: {}", univSiteDto);

            scrollRandom(driver);
            univSites.add(univSiteDto);
            sleepRandomSeconds(1,3);
        }
        return univSites;
    }

    /*************************************************************************/

    /**
     * 1초에서 3초 동안 쉬는 함수
     */
    private void sleepRandomSeconds(int minSeconds, int maxSeconds) {
        int sleepTime = (int) (Math.random() * (maxSeconds - minSeconds + 1) + minSeconds) * 1000;

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loginInsta() {
        // 서버내 파일에서 계정 정보 들고오도록
        Map<String,Object> idpw = FileUtil.readIDPWFromFile();
        username = idpw.get("username").toString();
        password = idpw.get("password").toString();

        WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        usernameInput.sendKeys(username);

        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        passwordInput.sendKeys(password);

        WebElement submitButton = driver.findElement(By.tagName("button")); // 태그명이 "button"인 경우
        submitButton.submit();
    }

    private void scrollToBottom(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // 스크롤을 최하단으로 이동
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // 스크롤이 완료될 때까지 대기 (이 부분은 선택 사항)
        // 적절한 대기 메서드를 사용하여 스크롤이 완료될 때까지 기다릴 수 있습니다.
        // 예를 들어, WebDriverWait를 사용하여 페이지의 특정 요소가 나타날 때까지 대기하는 방법이 있습니다.
    }

    private void scrollRandom(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        int yOffset = random.nextInt(500) + 100;  // 100에서 600까지의 랜덤한 값

        // 부드럽게 스크롤 이동
        jsExecutor.executeScript("window.scrollBy(0, arguments[0]);", yOffset);
    }

    /**
     * 페이지가 로딩될 때까지 기다리는 메서드
     */
    private void waitForPageLoad(WebDriver driver, String tagName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 페이지의 로딩이 완료되기를 기다리는 조건 설정
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(tagName)));
    }
}