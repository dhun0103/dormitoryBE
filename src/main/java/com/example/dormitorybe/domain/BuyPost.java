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
    String endtime;
    @Column(nullable = false)
    int counts;
    @Column(nullable = false)
    double price;
    @Column(nullable = false)
    String link;

    public BuyPost(BuyPostReqDto buyPostReqDto){
        this.title = buyPostReqDto.getTitle();
        this.category = buyPostReqDto.getCategory();
        this.endtime = buyPostReqDto.getEndtime();
        this.counts = buyPostReqDto.getCounts();
        this.price = buyPostReqDto.getPrice();
        this.link = buyPostReqDto.getLink();
    }

    public void updateBuyPost(BuyPostReqDto buyPostReqDto){
        this.title = buyPostReqDto.getTitle();
        this.category = buyPostReqDto.getCategory();
        this.endtime = buyPostReqDto.getEndtime();
        this.counts = buyPostReqDto.getCounts();
        this.price = buyPostReqDto.getPrice();
        this.link = buyPostReqDto.getLink();
    }

}
