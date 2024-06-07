package com.example.dormitorybe.service;

import com.example.dormitorybe.domain.SharePost;
import com.example.dormitorybe.dto.ReqDto.SharePostReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.exception.CustomException;
import com.example.dormitorybe.exception.ErrorCode;
import com.example.dormitorybe.repository.SharePostRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SharePostService {

    private final SharePostRepository sharePostRepository;
    private final Logger logger = LoggerFactory.getLogger(SharePostService.class);

    @Transactional
    public GlobalResDto<?> createSharePost(SharePostReqDto sharePostReqDto) {

        SharePost sharePost = new SharePost(sharePostReqDto);
        sharePostRepository.save(sharePost);

        return GlobalResDto.success(null, "success create sharePost");
    }

    @Transactional
    public GlobalResDto<?> updateSharePost(SharePostReqDto sharePostReqDto, Long sharePostId) {

        SharePost sharePost = sharePostRepository.findById(sharePostId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_POST)
                );

        sharePost.updateSharePost(sharePostReqDto);

        return GlobalResDto.success(null, "success update sharePost");
    }

    @Transactional
    public GlobalResDto<?> deleteSharePost(Long sharePostId) {

        SharePost sharePost = sharePostRepository.findById(sharePostId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_POST)
                );

        sharePostRepository.deleteById(sharePostId);

        return GlobalResDto.success(null, "success delete sharePost");
    }
}
