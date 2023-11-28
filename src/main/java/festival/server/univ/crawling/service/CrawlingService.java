package festival.server.univ.crawling.service;

import festival.server.univ.crawling.dto.RequestSearchParam;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Slf4j
@Service
public class CrawlingService {
    final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    final String WEB_DRIVER_PATH = "C:\\driver\\chromedriver-win64\\chromedriver159.exe";

    final String instaUrl = "https://instagram.com";

    //로그인 정보 입력
    final String username = "";
    final String password = "";

    WebDriverWait wait = null;
    WebDriver driver = null;
    public int processScraping(RequestSearchParam param) {
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("headless");

        driver = new ChromeDriver(options);

        try {
            driver.get(instaUrl);
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // 인스타그램 로그인
            loginInsta();

            // 대학별 찾기



            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
//            driver.close();
        }
    }

    private void loginInsta() {
        WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        usernameInput.sendKeys(username);

        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        passwordInput.sendKeys(password);

        WebElement submitButton = driver.findElement(By.tagName("button")); // 태그명이 "button"인 경우
        submitButton.submit();
    }

    private void searchUniv() {
        // 대학교 - 인스타URL 대조


    }
}