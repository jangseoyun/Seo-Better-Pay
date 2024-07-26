package com.yun.membership.adapter.axon;

import com.yun.membership.adapter.axon.model.CacheRegisterMembershipDto;
import com.yun.membership.application.port.in.event.RegisterMembershipEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class RegisterMembershipHandler {

    private final javax.cache.Cache<String, CacheRegisterMembershipDto> membershipCache;

    public RegisterMembershipHandler(org.springframework.cache.CacheManager springCacheManager) {
        // Spring CacheManager -> JCacheCacheManager 변환
        javax.cache.CacheManager jCacheManager =
                ((org.springframework.cache.jcache.JCacheCacheManager) springCacheManager).getCacheManager();

        this.membershipCache = jCacheManager.getCache("membershipCache", String.class, CacheRegisterMembershipDto.class);
    }

    @EventHandler
    public void on(RegisterMembershipEvent event) {
        // 이벤트로부터 Dto 생성
        CacheRegisterMembershipDto dto = new CacheRegisterMembershipDto(event.getMembershipId(), event.getMembershipPw(), event.getMembershipEmail());
        membershipCache.put(event.getMembershipId(), dto);
        System.out.println("event :" + membershipCache.get(dto.getMembershipId()));
    }

}
