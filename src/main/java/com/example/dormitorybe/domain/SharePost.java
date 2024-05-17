package com.example.dormitorybe.domain;

import com.example.dormitorybe.dto.ReqDto.BuyPostReqDto;
import com.example.dormitorybe.dto.ReqDto.SharePostReqDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class SharePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sharePostId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;

    public SharePost(SharePostReqDto sharePostReqDto){
        this.title = sharePostReqDto.getTitle();
        this.contents = sharePostReqDto.getContents();
    }

    public void updateSharePost(SharePostReqDto sharePostReqDto){
        this.title = sharePostReqDto.getTitle();
        this.contents = sharePostReqDto.getContents();
    }

}
