package com.example.dormitorybe.service;


import com.example.dormitorybe.domain.BuyPost;
import com.example.dormitorybe.domain.MatePost;
import com.example.dormitorybe.dto.ReqDto.BuyPostReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.exception.CustomException;
import com.example.dormitorybe.exception.ErrorCode;
import com.example.dormitorybe.repository.BuyPostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BuyPostService {

    private final BuyPostRepository buyPostRepository;
    private final Logger logger = LoggerFactory.getLogger(BuyPostService.class);

    @Transactional
    public GlobalResDto<?> createBuyPost(BuyPostReqDto buyPostReqDto) {

        BuyPost buyPost = new BuyPost(buyPostReqDto);
        buyPostRepository.save(buyPost);

        return GlobalResDto.success(null, "success create buyPost");
    }

    @Transactional
    public GlobalResDto<?> updateBuyPost(BuyPostReqDto buyPostReqDto, Long buyPostId) {

        BuyPost buyPost = buyPostRepository.findById(buyPostId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NotFoundPost)
                );

        buyPost.updateBuyPost(buyPostReqDto);

        return GlobalResDto.success(null, "success update buyPost");
    }

    public GlobalResDto<?> deleteBuyPost(Long buyPostId) {

        BuyPost buyPost = buyPostRepository.findById(buyPostId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NotFoundPost)
                );

        buyPostRepository.deleteById(buyPostId);

        return GlobalResDto.success(null, "success delete buyPost");
    }

}
