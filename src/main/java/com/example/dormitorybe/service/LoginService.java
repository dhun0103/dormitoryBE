package com.example.dormitorybe.service;

import com.example.dormitorybe.config.UserDetailsImpl;
import com.example.dormitorybe.domain.Member;
import com.example.dormitorybe.domain.RefreshToken;
import com.example.dormitorybe.dto.ReqDto.LoginReqDto;
import com.example.dormitorybe.dto.ReqDto.ProfileReqDto;
import com.example.dormitorybe.dto.ReqDto.SignUpReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.dto.ResDto.ProfileResDto;
import com.example.dormitorybe.exception.CustomException;
import com.example.dormitorybe.exception.ErrorCode;
import com.example.dormitorybe.jwt.JwtUtil;
import com.example.dormitorybe.jwt.TokenDto;
import com.example.dormitorybe.repository.MemberRepository;
import com.example.dormitorybe.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Transactional
    public GlobalResDto<?> signUp(SignUpReqDto signUpReqDto) {
        // 학번 중복 확인
        if(memberRepository.findByEmail(signUpReqDto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_HAVE_ID);
        }

        // 비밀번호 암호화
        signUpReqDto.setEncodePwd(passwordEncoder.encode(signUpReqDto.getPw()));

        Member member = new Member(signUpReqDto);
        memberRepository.save(member);

        return GlobalResDto.success(null, "success signup");
    }

    @Transactional
    public GlobalResDto<?> login(LoginReqDto loginReqDto, HttpServletResponse response) {
        // 계정 존재 확인
        Member member = memberRepository.findByEmail(loginReqDto.getEmail())
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_MEMBER)
                );

        // 비밀번호 맞는지 확인
        if (!passwordEncoder.matches(loginReqDto.getPw(), member.getPw())) {
            throw new CustomException(ErrorCode.NOT_MATCH_PASSWORD);
        }

        //토큰 발급
        TokenDto tokenDto = jwtUtil.createAllToken(loginReqDto.getEmail());
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByMemberEmail(loginReqDto.getEmail());

        // 로그아웃한 후 로그인을 다시 하는가?
        if(refreshToken.isPresent()) {
            RefreshToken refreshToken1 = refreshToken.get().updateToken(tokenDto.getRefreshToken());
            refreshTokenRepository.save(refreshToken1);
        } else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginReqDto.getEmail());
            refreshTokenRepository.save(newToken);
        }

        //토큰을 header에 넣어서 클라이언트에게 전달하기
        setHeader(response, tokenDto);

        return GlobalResDto.success(null, "success login");
    }

    @Transactional
    public GlobalResDto<?> profile(UserDetailsImpl userDetails) {

        Member member = userDetails.getMember();

//        Member member = memberRepository.findByEmail(profileReqDto.getEmail())
//                .orElseThrow(
//                        () -> new CustomException(ErrorCode.NOT_FOUND_MEMBER)
//                );

        ProfileResDto profileResDto = new ProfileResDto(member);

        return GlobalResDto.success(profileResDto, "success profile");
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
        log.info("Access_Token: " + tokenDto.getAccessToken());
        log.info("Refresh_Token: " + tokenDto.getRefreshToken());
    }

    @Transactional
    public GlobalResDto<?> logout(UserDetailsImpl userDetails) {

        try{

            refreshTokenRepository.deleteAllByMemberEmail(userDetails.getMember().getEmail());
            return GlobalResDto.success(null, "success logout");

        }catch (Exception e) {
            logger.error("Failed to logout", e);
            return GlobalResDto.fail("Failed to logout");
        }

    }
}
