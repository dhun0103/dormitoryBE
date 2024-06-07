package com.example.dormitorybe.service;


import com.example.dormitorybe.config.UserDetailsImpl;
import com.example.dormitorybe.domain.BuyPost;
import com.example.dormitorybe.dto.ReqDto.BuyPostReqDto;
import com.example.dormitorybe.dto.ResDto.BuyPostResDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.exception.CustomException;
import com.example.dormitorybe.exception.ErrorCode;
import com.example.dormitorybe.repository.BuyPostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BuyPostService {

    private final BuyPostRepository buyPostRepository;
    private final Logger logger = LoggerFactory.getLogger(BuyPostService.class);

    @Transactional
    public GlobalResDto<?> createBuyPost(BuyPostReqDto buyPostReqDto, UserDetailsImpl userDetails) {

        BuyPost buyPost = new BuyPost(buyPostReqDto, userDetails.getMember());
        buyPostRepository.save(buyPost);

        return GlobalResDto.success(null, "success create buyPost");
    }

    @Transactional
    public GlobalResDto<?> updateBuyPost(BuyPostReqDto buyPostReqDto, Long buyPostId) {

        BuyPost buyPost = buyPostRepository.findById(buyPostId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_POST)
                );

        buyPost.updateBuyPost(buyPostReqDto);

        return GlobalResDto.success(null, "success update buyPost");
    }

    public GlobalResDto<?> deleteBuyPost(Long buyPostId) {

        BuyPost buyPost = buyPostRepository.findById(buyPostId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_POST)
                );

        buyPostRepository.deleteById(buyPostId);

        return GlobalResDto.success(null, "success delete buyPost");
    }

    @Transactional
    public GlobalResDto<?> getAllBuyPost() {
        List<BuyPost> buyPostList = buyPostRepository.findAll();

        List<BuyPostResDto> buyPostResDtoList = buyPostList.stream()
                .map(BuyPostResDto::new)
                .collect(Collectors.toList());

        return GlobalResDto.success(buyPostResDtoList, "success check buyPosts");
    }
}
