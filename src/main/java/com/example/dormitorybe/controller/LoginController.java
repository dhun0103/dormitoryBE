package com.example.dormitorybe.controller;

import com.example.dormitorybe.dto.ReqDto.MemberReqDto;
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
    public GlobalResDto<?> SignUp(@RequestBody MemberReqDto memberReqDto){

        return loginService.signUp(memberReqDto);
    }

    @PostMapping("/login")
    public GlobalResDto<?> Login(@RequestBody MemberReqDto memberReqDto){

        return loginService.login(memberReqDto);
    }
}
