package example.d05._2WebCrawling;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrawlingService {

    public Map<String, String> task1() {
        // 크롬 설치
        WebDriverManager.chromedriver().setup();
        // 크롬 옵션 (크롬 사용 시 브라우저가 켜지지 않도록 설정)
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new", "--disable-gpu");
        // 웹 드라이버(Selenium) 객체 생성
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        // 웹 주소 가져오기
        String url = "https://weather.daum.net/";
        webDriver.get(url);
        // 동적 페이지 로딩을 위해 5초간 대기 -> WebDriverWait(셀레니움 객체, 대기 시간)
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        // 가져올 HTML, CSS 식별하기 (상위 식별자를 넣어서 중복을 걸러내는 것이 권장됨)
        // 자손 선택자: 식별자1 식별자2 , 자식 선택자: 식별자1 > 식별자2
        // By.cssSelector(크롤링할 CSS 선택자);
        // 예시는 지역, 온도, 상태
        WebElement location = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".info_location .tit_location")));
        System.out.println("location = " + location);
        WebElement degree = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".wrap_weather .num_deg")));
        System.out.println("degree = " + degree);
        WebElement status = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".wrap_weather .txt_sub")));
        System.out.println("status = " + status);

        // 가져온 요소의 텍스트 추출 및 저장
        Map<String, String> map = new HashMap<>();
        map.put("위치", location.getText());
        map.put("온도", degree.getText());
        map.put("상태", status.getText());

        // 웹 드라이버 종료 후 반환
        webDriver.quit();
        return map;
    }

    public List<String> task2() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new", "--disable-gpu");
        WebDriver webDriver = new ChromeDriver(chromeOptions);

        String url = "https://cgv.co.kr/cnm/cgvChart/movieChart/89833";
        webDriver.get(url);

        List<String> list = new ArrayList<>();
        for (int i=1; i<=5; i++) {
            List<WebElement> webElements = webDriver.findElements(By.cssSelector(".reveiwCard_txt__RrTgu"));
            int startCount = list.size();
            for ( WebElement review : webElements ) {
                String text = review.getText();
                // 이미 존재하는 리뷰일 시 생략
                if (list.contains(text)) continue;
                list.add(text);
            }
            int endCount = list.size();
            // 리스트에 변동 사항이 없는 경우 (데이터가 하나도 들어가지 않은 경우) 반복문 종료 (스크롤 작업도 종료)
            if (startCount == endCount) break;

            // 자바에서 JS 사용
            // 자바스크립트 실행 객체 (화면의 최하단으로 스크롤 이동)
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }
}
