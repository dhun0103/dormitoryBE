package com.example.dormitorybe.service;

import com.example.dormitorybe.domain.MatePost;
import com.example.dormitorybe.domain.Member;
import com.example.dormitorybe.domain.NoticePost;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.dto.ReqDto.NoticePostReqDto;
import com.example.dormitorybe.exception.CustomException;
import com.example.dormitorybe.exception.ErrorCode;
import com.example.dormitorybe.repository.NoticePostRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticePostService {

    private final NoticePostRepository noticePostRepository;

    @Transactional
    public GlobalResDto<?> createNoticePost(NoticePostReqDto noticePostReqDto) {

        NoticePost noticePost = new NoticePost(noticePostReqDto);
        noticePostRepository.save(noticePost);

        return GlobalResDto.success(null, "success create noticePost");
    }

    public GlobalResDto<?> createNoticePostsFromCrawling(List<NoticePostReqDto> noticePostReqDtos) {
        List<NoticePost> noticePosts = noticePostReqDtos.stream()
                .map(NoticePost::new)
                .collect(Collectors.toList());
        noticePostRepository.saveAll(noticePosts);
        return GlobalResDto.success(null, "success create noticePost");
    }

    public GlobalResDto<?> crawlNoticePost(String URL, int noticeType) {

        List<NoticePostReqDto> noticePostReqDtos = new ArrayList<>();

        System.out.println("###START###");

        try {
            //TODO(path local로 안하는 거 따로 확인)
            Path path = Paths.get("H:\\spring\\dormitoryBE\\chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", path.toString());

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-popup-blocking");   // 팝업 안띄움
            options.addArguments("headless");   // 웹페이지(브라우저) 안 띄움
            options.addArguments("--disable-gpu");  // gpu 비활성화
            options.addArguments("--blink-settings=imagesEnabled=false");   // 이미지 다운 안받음

            WebDriver driver = new ChromeDriver(options);

//            ChromeDriver driver = new ChromeDriver(options);
//            driver.executeScript("window.open('about:blank','_blank');");//빈탭생성
//            List<String> tabs = new ArrayList<String>(driver.getWindowHandles()); //탭목록 가져오기
//            driver.switchTo().window(tabs.get(0)); //첫번쨰 탭으로 전환

            driver.get(URL);

            List<WebElement> contents = driver.findElements(By.cssSelector("div._fnctWrap > form:nth-child(2) > div > table > tbody > tr"));

            if (contents.size() > 0) {
                for (WebElement content : contents) {
                    String title = content.findElement(By.cssSelector("td.td-subject > a > strong")).getText();

                    String date = content.findElement(By.cssSelector("td.td-date")).getText();
                    String url = content.findElement(By.cssSelector("td.td-subject > a")).getAttribute("href");

                    NoticePostReqDto noticePostReqDto = new NoticePostReqDto(title, date, url, noticeType);
                    noticePostReqDtos.add(noticePostReqDto);


                }
            }

            driver.quit();
            System.out.println("####END####");

            createNoticePostsFromCrawling(noticePostReqDtos);
            return GlobalResDto.success(noticePostReqDtos, "success create noticePost");

        }catch(Exception e){
            System.out.println("####crawling error####");
            System.out.println(e);
            return GlobalResDto.fail("Failed to create noticePost");
        }

    }


}
