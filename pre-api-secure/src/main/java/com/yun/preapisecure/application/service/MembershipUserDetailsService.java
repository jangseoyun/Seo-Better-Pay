package com.yun.preapisecure.application.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MembershipUserDetailsService implements UserDetailsService {

    //userreadusecase
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //해당 username의 회원이 존재하는지 확인
        // 존재하지 않으면 예외
        //권한 설정

        return null;
    }
}
