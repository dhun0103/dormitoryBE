package com.example.dormitorybe.dto.ResDto;

import com.example.dormitorybe.domain.BuyPost;
import com.example.dormitorybe.domain.MatePost;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuyPostResDto {

    private Long buyPostId;

    String title;
    String category;
    String endDay;
    String endTime;
    int counts;
    double price;
    String link;

    public BuyPostResDto(BuyPost buyPost){
        this.buyPostId=buyPost.getBuyPostId();
        this.title=buyPost.getTitle();
        this.category=buyPost.getCategory();
        this.endDay=buyPost.getEndDay();
        this.endTime=buyPost.getEndTime();
        this.counts=buyPost.getCounts();
        this.price=buyPost.getPrice();
        this.link=buyPost.getLink();
    }
}
