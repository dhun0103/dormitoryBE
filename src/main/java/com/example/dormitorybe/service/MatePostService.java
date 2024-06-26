package com.example.dormitorybe.service;

import com.example.dormitorybe.config.UserDetailsImpl;
import com.example.dormitorybe.domain.MatePost;
import com.example.dormitorybe.dto.ReqDto.MatePostReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.dto.ResDto.MatePostDetailDto;
import com.example.dormitorybe.dto.ResDto.MatePostResDto;
import com.example.dormitorybe.exception.CustomException;
import com.example.dormitorybe.exception.ErrorCode;
import com.example.dormitorybe.repository.MatePostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MatePostService {
    private final MatePostRepository matePostRepository;
    private final Logger logger = LoggerFactory.getLogger(MatePostService.class);

    @Transactional
    public GlobalResDto<?> createMatePost(MatePostReqDto matePostReqDto, UserDetailsImpl userDetails) {



        MatePost matePost = new MatePost(matePostReqDto, userDetails.getMember());
        matePostRepository.save(matePost);

        return GlobalResDto.success(null, "success create matePost");

    }

    @Transactional
    public GlobalResDto<?> updateMatePost(MatePostReqDto matePostReqDto, Long matePostId) {

        MatePost matePost = matePostRepository.findById(matePostId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_POST)
                );

        matePost.updateMatePost(matePostReqDto);

        return GlobalResDto.success(null, "success update matePost");
    }

    @Transactional
    public GlobalResDto<?> deleteMatePost(Long matePostId) {

        MatePost matePost = matePostRepository.findById(matePostId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_POST)
                );

        matePostRepository.deleteById(matePostId);

        return GlobalResDto.success(null, "success delete matePost");
    }

    @Transactional
    public GlobalResDto<?> getAllMatePost() {

        List<MatePost> matePostList = matePostRepository.findAll();

        List<MatePostResDto> matePostResDtoList = matePostList.stream()
                .map(MatePostResDto::new)
                .collect(Collectors.toList());

        return GlobalResDto.success(matePostResDtoList, "success check matePosts");
    }

    @Transactional
    public GlobalResDto<?> getMatePost(Long matePostId) {

        MatePost matePost = matePostRepository.findById(matePostId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_POST)
                );

        MatePostDetailDto matePostDetailDto = new MatePostDetailDto(matePost);

        return GlobalResDto.success(matePostDetailDto, "success check matePost");
    }
}
