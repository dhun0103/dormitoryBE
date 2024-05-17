package com.example.dormitorybe.controller;

import com.example.dormitorybe.dto.ReqDto.SharePostReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.SharePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SharePostController {

    private final SharePostService sharePostService;

    @PostMapping("/share-posts")
    public GlobalResDto<?> createBuyPost(@RequestBody SharePostReqDto sharePostReqDto){

        return sharePostService.createSharePost(sharePostReqDto);
    }

    @PutMapping("/share-posts/{sharePostId}")
    public GlobalResDto<?> updateMatePost(@RequestBody SharePostReqDto sharePostReqDto,
                                          @PathVariable Long sharePostId){

        return sharePostService.updateSharePost(sharePostReqDto, sharePostId);
    }

    @DeleteMapping("/share-posts/{sharePostId}")
    public GlobalResDto<?> deleteBuyPost(@PathVariable Long sharePostId){

        return sharePostService.deleteSharePost(sharePostId);
    }
}