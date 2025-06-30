package com.example.bcsd.controller;


import com.example.bcsd.Dto.LoginResponse;
import com.example.bcsd.Dto.loginRequest;
import com.example.bcsd.Service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String checkLogin() {

        return "OK";
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody loginRequest loginRequest,
                               HttpServletRequest request) {

        LoginResponse loginResponse = memberService.login(loginRequest);
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
        HttpSession session = request.getSession(true);
        session.setAttribute("LOGIN_MEMBER_EMAIL", loginResponse.email());
        session.setMaxInactiveInterval(30 * 60);
        return LoginResponse.of(
                loginResponse.email()

        );

    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
