package com.example.dormitorybe.controller;

import com.example.dormitorybe.config.UserDetailsImpl;
import com.example.dormitorybe.dto.ReqDto.MatePostReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.MatePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MatePostController {

    private final MatePostService matePostService;

    @PostMapping("/mate-posts")
    public GlobalResDto<?> createMatePost(@RequestBody MatePostReqDto matePostReqDto, @AuthenticationPrincipal UserDetailsImpl userDetails){

        return matePostService.createMatePost(matePostReqDto, userDetails);
    }

    @PutMapping("/mate-posts/{matePostId}")
    public GlobalResDto<?> updateMatePost(@RequestBody MatePostReqDto matePostReqDto,
                                          @PathVariable Long matePostId){

        return matePostService.updateMatePost(matePostReqDto, matePostId);
    }

    @DeleteMapping("/mate-posts/{matePostId}")
    public GlobalResDto<?> deleteMatePost(@PathVariable Long matePostId){

        return matePostService.deleteMatePost(matePostId);
    }

    @GetMapping("/mate-posts/allposts")
    public GlobalResDto<?> getAllMatePost(){

        return matePostService.getAllMatePost();
    }

    @GetMapping("/mate-posts/{matePostId}")
    public GlobalResDto<?> getMatePost(@PathVariable Long matePostId){

        return matePostService.getMatePost(matePostId);
    }
}
