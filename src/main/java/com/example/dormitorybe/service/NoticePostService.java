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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
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

    public GlobalResDto<?> crawlNoticePost(){
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
            //페이지 갯수

            String pageURL = "https://kulhouse.konkuk.ac.kr/home/sub04/sub04_01.asp";

            for(int page=1; page<=4; page++) {
                String urlcode = "?intNowPage="+page+"&board_nm=kulhouse_notice&search_m=&search_t=&intNoticeCnt=27";
                String URL = pageURL+urlcode;
                System.out.println(URL);
                driver.get(URL);

                List<WebElement> contents = driver.findElements(By.cssSelector("#content > div > form > table > tbody > tr"));

                if (contents.size() > 0) {
                    for (WebElement content : contents) {
                        String title = content.findElement(By.cssSelector("td:nth-child(2)")).getText();
                        String writer = content.findElement(By.cssSelector("td:nth-child(3)")).getText();
                        String date = content.findElement(By.cssSelector("td:nth-child(4)")).getText();
                        String visits = content.findElement(By.cssSelector("td:nth-child(5)")).getText();
                        String onclickurl = content.findElement(By.cssSelector("td:nth-child(2) > a")).getAttribute("onclick");
                        int pgnum = Integer.parseInt(onclickurl.substring(onclickurl.lastIndexOf("=") + 1, onclickurl.length() - 2));
                        String url = "https://kulhouse.konkuk.ac.kr/home/sub04/sub04_01_v.asp?intNowPage=1&board_nm=kulhouse_notice&idx="+pgnum;

                        System.out.println(title+"\n"+writer+"\n"+date+"\n"+visits+"\n"+pgnum+"\n"+url+"\n");

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

    /*
    //public GlobalResDto<?> crawlNoticePost(String pageURL, int noticeType) {
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


            //페이지 갯수
            driver.get(pageURL);
            WebElement totlapage = driver.findElement(By.cssSelector("div._fnctWrap > form:nth-child(3) > div > div > a._last"));
            System.out.println(totlapage);
            System.out.println(totlapage.getAttribute("href"));
            String linkText = totlapage.getAttribute("href");
            int pageNumber = Integer.parseInt(linkText.replaceAll("\\D+", ""));
            System.out.println(pageNumber);


            for(int page=1; page<=pageNumber; page++) {
                String urlcode = "?page="+page+"&isViewMine=false";
                String URL = pageURL+urlcode;
                System.out.println(URL);
                driver.get(URL);

//            WebElement paginationElement = driver.findElement(By.cssSelector("div.paging > span.total"));
//            String totalPagesText = paginationElement.getText();
//            int totalPages = Integer.parseInt(totalPagesText.replaceAll("[^0-9]", ""));
//            System.out.println("{"+totalPagesText);


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

    private static String generatePageURL(String decodedURL, int pageNumber) {
        String[] parts = decodedURL.split("\\?");
        String baseURL = parts[0];
        String queryParams = parts[1];

        String pageParam = "page=" + pageNumber;
        if (queryParams.contains("page=")) {
            queryParams = queryParams.replaceAll("page=\\d+", pageParam);
        } else {
            queryParams += "&" + pageParam;
        }

        return baseURL + "?" + queryParams;
    }*/


}
