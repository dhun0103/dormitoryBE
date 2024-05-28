package com.example.dormitorybe.service;

import com.example.dormitorybe.domain.Member;
import com.example.dormitorybe.dto.ReqDto.MemberReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.exception.CustomException;
import com.example.dormitorybe.exception.ErrorCode;
import com.example.dormitorybe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public GlobalResDto<?> signUp(MemberReqDto memberReqDto) {

        Member member = new Member(memberReqDto);
        memberRepository.save(member);

        return GlobalResDto.success(null, "success signup");
    }

    @Transactional
    public GlobalResDto<?> login(MemberReqDto memberReqDto) {

        Member member = memberRepository.findByStudentNum(memberReqDto.getStudentNum())
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_MEMBER)
                );

        if (!passwordEncoder.matches(memberReqDto.getPw(), member.getPw())) {
            throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        return GlobalResDto.success(null, "success login");
    }
}
