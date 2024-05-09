package com.example.dormitorybe.service;

import com.example.dormitorybe.domain.MatePost;
import com.example.dormitorybe.dto.ReqDto.MatePostReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.exception.CustomException;
import com.example.dormitorybe.exception.ErrorCode;
import com.example.dormitorybe.repository.MatePostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MatePostService {
    private final MatePostRepository matePostRepository;
    private final Logger logger = LoggerFactory.getLogger(MatePostService.class);

    @Transactional
    public GlobalResDto<?> createMatePost(MatePostReqDto matePostReqDto) {

        MatePost matePost = new MatePost(matePostReqDto);
        matePostRepository.save(matePost);

        return GlobalResDto.success(null, "success create matePost");
    }

    public GlobalResDto<?> updateMatePost(MatePostReqDto matePostReqDto, Long matePostId) {

        MatePost matePost = matePostRepository.findById(matePostId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NotFoundPost)
                );

        matePost.updateMatePost(matePostReqDto);

        return GlobalResDto.success(null, "success update matePost");
    }
}
