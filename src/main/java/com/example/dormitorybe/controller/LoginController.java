package com.example.dormitorybe.controller;

import com.example.dormitorybe.config.UserDetailsImpl;
import com.example.dormitorybe.dto.ReqDto.LoginReqDto;
import com.example.dormitorybe.dto.ReqDto.ProfileReqDto;
import com.example.dormitorybe.dto.ReqDto.SignUpReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/signup")
    public GlobalResDto<?> SignUp(@RequestBody SignUpReqDto signUpReqDto) {

        return loginService.signUp(signUpReqDto);
    }

    @PostMapping("/login")
    public GlobalResDto<?> Login(@RequestBody LoginReqDto loginReqDto, HttpServletResponse response) {

        return loginService.login(loginReqDto, response);
    }

    @GetMapping("/profile")
    public GlobalResDto<?> Profile(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        return loginService.profile(userDetails);
    }
}
