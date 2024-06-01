package com.example.dormitorybe.service;

import com.example.dormitorybe.domain.NoticePost;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.dto.ReqDto.NoticePostReqDto;
import com.example.dormitorybe.repository.NoticePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticePostService {

    private final NoticePostRepository noticePostRepository;

    @Transactional
    public GlobalResDto<?> createNoticePost(NoticePostReqDto noticePostReqDto) {

        NoticePost noticePost = new NoticePost(noticePostReqDto);
        noticePostRepository.save(noticePost);

        return GlobalResDto.success(null, "success create noticePost");
    }

    public GlobalResDto<?> createNoticePostsFromCrawling(List<NoticePostReqDto> noticePostReqDtos) {
        List<NoticePost> noticePosts = noticePostReqDtos.stream()
                .map(NoticePost::new)
                .collect(Collectors.toList());
        noticePostRepository.saveAll(noticePosts);
        return GlobalResDto.success(null, "success create noticePost");
    }

}
