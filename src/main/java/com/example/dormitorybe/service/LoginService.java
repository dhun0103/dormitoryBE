package com.example.dormitorybe.service;

import com.example.dormitorybe.domain.Member;
import com.example.dormitorybe.dto.ReqDto.MemberReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final MemberRepository memberRepository;

    @Transactional
    public GlobalResDto<?> signUp(MemberReqDto memberReqDto) {

        Member member = new Member(memberReqDto);
        memberRepository.save(member);

        return GlobalResDto.success(null, "success signup");
    }
    @Transactional
    public GlobalResDto<?> login(MemberReqDto memberReqDto) {

        return GlobalResDto.success(null, "success login");
    }
}
