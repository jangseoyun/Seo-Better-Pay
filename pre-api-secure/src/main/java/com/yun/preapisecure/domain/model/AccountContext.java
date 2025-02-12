package com.yun.preapisecure.domain.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AccountContext extends User {

    private final RequestAccessMembership requestAccessMembership;

    public AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities, RequestAccessMembership requestAccessMembership) {
        super(username, password, authorities);
        this.requestAccessMembership = requestAccessMembership;
    }

    public RequestAccessMembership getRequestAccessMembership() {
        return requestAccessMembership;
    }
}
