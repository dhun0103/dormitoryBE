package com.example.dormitorybe.domain;

import com.example.dormitorybe.dto.ReqDto.BuyPostReqDto;
import com.example.dormitorybe.dto.ReqDto.MatePostReqDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
public class BuyPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyPostId;
    @Column(nullable = false)
    String title;
    @Column(nullable = false)
    String category;
    @Column(nullable = false)
    String endDay;
    @Column(nullable = false)
    String endTime;
    @Column(nullable = false)
    int counts;
    @Column(nullable = false)
    double price;
    @Column(nullable = false)
    String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public BuyPost(BuyPostReqDto buyPostReqDto, Member member){
        this.title = buyPostReqDto.getTitle();
        this.category = buyPostReqDto.getCategory();
        this.endDay = buyPostReqDto.getEndDay();
        this.endTime = buyPostReqDto.getEndTime();
        this.counts = buyPostReqDto.getCounts();
        this.price = buyPostReqDto.getPrice();
        this.link = buyPostReqDto.getLink();
        this.member = member;
    }

    public void updateBuyPost(BuyPostReqDto buyPostReqDto){
        this.title = buyPostReqDto.getTitle();
        this.category = buyPostReqDto.getCategory();
        this.endDay = buyPostReqDto.getEndDay();
        this.endTime = buyPostReqDto.getEndTime();
        this.counts = buyPostReqDto.getCounts();
        this.price = buyPostReqDto.getPrice();
        this.link = buyPostReqDto.getLink();
    }

}
