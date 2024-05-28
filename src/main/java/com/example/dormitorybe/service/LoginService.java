package com.example.dormitorybe.service;

import com.example.dormitorybe.domain.Member;
import com.example.dormitorybe.dto.ReqDto.LoginReqDto;
import com.example.dormitorybe.dto.ReqDto.SignUpReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.exception.CustomException;
import com.example.dormitorybe.exception.ErrorCode;
import com.example.dormitorybe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final MemberRepository memberRepository;

    @Transactional
    public GlobalResDto<?> signUp(SignUpReqDto signUpReqDto) {

        Member member = new Member(signUpReqDto);
        memberRepository.save(member);

        return GlobalResDto.success(null, "success signup");
    }

    @Transactional
    public GlobalResDto<?> login(LoginReqDto loginReqDto) {

        Member member = memberRepository.findByStudentNum(loginReqDto.getStudentNum())
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_MEMBER)
                );

        if (!loginReqDto.getPw().equals(member.getPw())) {
            throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        return GlobalResDto.success(null, "success login");
    }
}
