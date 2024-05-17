package com.example.dormitorybe.controller;

import com.example.dormitorybe.dto.ReqDto.MatePostReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.MatePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MatePostController {

    private final MatePostService matePostService;

    @PostMapping("/mate-posts")
    public GlobalResDto<?> createMatePost(@RequestBody MatePostReqDto matePostReqDto){

        return matePostService.createMatePost(matePostReqDto);
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
}
