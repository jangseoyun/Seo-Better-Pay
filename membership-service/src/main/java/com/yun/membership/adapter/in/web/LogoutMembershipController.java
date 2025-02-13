package com.yun.membership.adapter.in.web;

import com.yun.common.anotation.WebAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/membership")
public class LogoutMembershipController {

    //로그아웃 어노테이션
    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {

    }

    @GetMapping("/denied")
    public void accessDenied(@RequestParam(value = "exception", required = false) String exception) {

    }
}
