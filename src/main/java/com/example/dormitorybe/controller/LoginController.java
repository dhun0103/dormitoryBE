package com.example.dormitorybe.controller;

import com.example.dormitorybe.dto.ReqDto.LoginReqDto;
import com.example.dormitorybe.dto.ReqDto.SignUpReqDto;
import com.example.dormitorybe.dto.ResDto.GlobalResDto;
import com.example.dormitorybe.service.LoginService;
import lombok.RequiredArgsConstructor;
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
    public GlobalResDto<?> Login(@RequestBody LoginReqDto loginReqDto) {

        return loginService.login(loginReqDto);
    }
}
