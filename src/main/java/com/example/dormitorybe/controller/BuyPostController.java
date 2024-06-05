package com.example.dormitorybe.controller;

import com.example.dormitorybe.config.UserDetailsImpl;
import com.example.dormitorybe.dto.ReqDto.BuyPostReqDto;
import com.example.dormitorybe.dto.ReqDto.MatePostReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.BuyPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BuyPostController {

    private final BuyPostService buyPostService;

    @PostMapping("/buy-posts")
    public GlobalResDto<?> createBuyPost(@RequestBody BuyPostReqDto buyPostReqDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return buyPostService.createBuyPost(buyPostReqDto, userDetails);
    }

    @PutMapping("/buy-posts/{buyPostId}")
    public GlobalResDto<?> updateMatePost(@RequestBody BuyPostReqDto buyPostReqDto,
                                          @PathVariable Long buyPostId){

        return buyPostService.updateBuyPost(buyPostReqDto, buyPostId);
    }

    @DeleteMapping("/buy-posts/{buyPostId}")
    public GlobalResDto<?> deleteBuyPost(@PathVariable Long buyPostId){

        return buyPostService.deleteBuyPost(buyPostId);
    }

    @GetMapping("/buy-posts/allposts")
    public GlobalResDto<?> getAllBuyPost(){

        return buyPostService.getAllBuyPost();
    }
}
