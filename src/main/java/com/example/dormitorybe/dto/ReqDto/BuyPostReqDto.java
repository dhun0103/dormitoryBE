package com.example.dormitorybe.dto.ReqDto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuyPostReqDto {

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

}
