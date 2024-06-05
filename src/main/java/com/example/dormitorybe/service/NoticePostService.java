package com.example.dormitorybe.service;

import com.example.dormitorybe.domain.NoticePost;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.dto.ReqDto.NoticePostReqDto;
import com.example.dormitorybe.repository.NoticePostRepository;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

    public GlobalResDto<?> getLatestThreeNoticePosts() {
        crawlNoticePost("https://kulhouse.konkuk.ac.kr/home/sub04/sub04_01.asp" +
                "?intNowPage=1&board_nm=kulhouse_notice&search_m=&search_t=&intNoticeCnt=27");
        List<NoticePost> noticePosts = noticePostRepository.findLatestThreeNoticePosts();
        return GlobalResDto.success(noticePosts, "success get latest 3 notice posts");
    }



    public GlobalResDto<?> crawlNoticePost(String pageURL){
        List<NoticePostReqDto> noticePostReqDtos = new ArrayList<>();

        System.out.println("###START###");

        try {
            String currentDir = System.getProperty("user.dir");
            String driverPath = currentDir + "\\chromedriver.exe";
            System.out.println(driverPath);

            // 드라이버 경로 설정
            System.setProperty("webdriver.chrome.driver", driverPath);

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-popup-blocking");   // 팝업 안띄움
            options.addArguments("headless");   // 웹페이지(브라우저) 안 띄움
            options.addArguments("--disable-gpu");  // gpu 비활성화
            options.addArguments("--blink-settings=imagesEnabled=false");   // 이미지 다운 안받음

            WebDriver driver = new ChromeDriver(options);

            //url parsing
            String[] urlparts = pageURL.split("\\?");
            String[] urlelements = urlparts[1].split("&");
            urlelements[0] = urlelements[0].substring(0, urlelements[0].indexOf("=")+1);

            //total page
            driver.get(pageURL);
            String pagestr = driver.findElement(By.cssSelector("#content > div > form > div.posRelative > div > a.btnNextLast")).getAttribute("href");
            int totalpage = Integer.parseInt(pagestr.substring(pagestr.indexOf("intNowPage=") + 11, pagestr.indexOf("&", pagestr.indexOf("intNowPage="))));

            for(int page=1; page<=totalpage; page++) {
                String URL = urlparts[0]+"?"+urlelements[0]+page+"&"+urlelements[1]+"&"+urlelements[2]+"&"+urlelements[3]+"&"+urlelements[4];
                driver.get(URL);

                List<WebElement> contents = driver.findElements(By.cssSelector("#content > div > form > table > tbody > tr"));

                if (contents.size() > 0) {
                    for (WebElement content : contents) {
                        String title = content.findElement(By.cssSelector("td:nth-child(2)")).getText();
                        String writer = content.findElement(By.cssSelector("td:nth-child(3)")).getText();
                        String date = content.findElement(By.cssSelector("td:nth-child(4)")).getText();
                        String visits = content.findElement(By.cssSelector("td:nth-child(5)")).getText();
                        //url parsing
                        String onclickurl = content.findElement(By.cssSelector("td:nth-child(2) > a")).getAttribute("onclick");
                        int pgnum = Integer.parseInt(onclickurl.substring(onclickurl.lastIndexOf("=") + 1, onclickurl.length() - 2));
                        String url = urlparts[0].replace(".asp", "_v.asp")+"?intNowPage="+page+"&"+urlelements[1]+"&idx="+pgnum;

                        NoticePostReqDto noticePostReqDto = new NoticePostReqDto(title, writer, date, visits, url);
                        noticePostReqDtos.add(noticePostReqDto);
                    }
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
