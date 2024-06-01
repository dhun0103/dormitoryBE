package com.example.dormitorybe.controller;

import com.example.dormitorybe.domain.NoticePost;
import com.example.dormitorybe.dto.ReqDto.NoticePostReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.NoticePostService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class SeleniumController {
    private String URL = "https://www.konkuk.ac.kr/konkuk/2238/subview.do";

    private final NoticePostService noticePostService;

    public SeleniumController(NoticePostService noticePostService) {
        this.noticePostService = noticePostService;
    }



    @GetMapping("/notices")
    public GlobalResDto<?>  selenium(){

        String result = "";

        List<WebElement> contents;

        List<NoticePostReqDto> noticePostReqDtos = new ArrayList<>();

        System.out.println("###START###");

        try{
            //TODO(path local로 안하는 거 따로 확인)
            Path path = Paths.get("H:\\spring\\dormitoryBE\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", path.toString());

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-popup-blocking");   // 팝업 안띄움
            options.addArguments("headless");   // 웹페이지(브라우저) 안 띄움
            options.addArguments("--disable-gpu");  // gpu 비활성화
            options.addArguments("--blink-settings=imagesEnabled=false");   // 이미지 다운 안받음

            WebDriver driver = new ChromeDriver(options);
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 드라이버가 실행된 후 10초 기다림

            driver.get(URL);

//             가져올 데이터가 로딩될 때까지 기다림
//        webDriverWait.until(
//                ExpectedCondition.presenceOfElementLocated(By.cssSelector("div._fnctWrap > form:nth-child(2) > div > table > tbody > tr")
//        );

            contents = driver.findElements(By.cssSelector("div._fnctWrap > form:nth-child(2) > div > table > tbody > tr"));

            if(contents.size() > 0) { //데이터 있으면
                for (WebElement content : contents) {
                    //result에 저장
                    String title = content.findElement(By.cssSelector("td.td-subject > a > strong")).getText();
                    String date = content.findElement(By.cssSelector("td.td-date")).getText();
                    WebElement url = content.findElement(By.cssSelector("td.td-subject > a"));

                    NoticePostReqDto noticePostReqDto = new NoticePostReqDto(title, date, url.getAttribute("href"));
                    noticePostReqDtos.add(noticePostReqDto);

                    //result += "공지 title / 작성일 : " + title + "/" + date + "<br />";
                    //result += "공지 url : " + url.getAttribute("href") + "<br />";

                }
            }

            driver.quit();
            System.out.println("####END####");

            noticePostService.createNoticePostsFromCrawling(noticePostReqDtos);
            return GlobalResDto.success(noticePostReqDtos, "success create noticePost");

        }catch(Exception e){
            System.out.println("####crawling error####");
            return GlobalResDto.fail("Failed to create noticePost");
        }



        //return result;
    }

}
