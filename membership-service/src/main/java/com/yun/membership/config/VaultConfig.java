package com.yun.membership.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.core.VaultKeyValueOperations;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultToken;

//vault 설정
@Slf4j
@Configuration
public class VaultConfig {
    private String vaultToken;
    private String vaultScheme;
    private String vaultHost;
    private int vaultPort;

    public VaultConfig(@Value("${spring.cloud.vault.token}") String vaultToken,
                       @Value("${spring.cloud.vault.scheme}") String vaultScheme,
                       @Value("${spring.cloud.vault.host}") String vaultHost,
                       @Value("${spring.cloud.vault.port}") int vaultPort) {
        this.vaultToken = vaultToken;
        this.vaultScheme = vaultScheme;
        this.vaultHost = vaultHost;
        this.vaultPort = vaultPort;
    }

    @Bean
    public VaultTemplate vaultTemplate() {
        VaultEndpoint vaultEndpoint = VaultEndpoint.create(vaultHost, vaultPort);
        vaultEndpoint.setScheme(vaultScheme);//http
        // vaultScheme : http, https
        // https 테스트를 로컬에서 하기 어렵기 때문에 http를 사용해야한다
        // 실제 운영시에는 https로 해야함
        VaultTemplate template = new VaultTemplate(vaultEndpoint, () -> VaultToken.of(vaultToken));//vault 초기화
        //vault 암호화된 키 가져오는지 확인
        VaultKeyValueOperations ops = template.opsForKeyValue("kv-v1/data/encrypt", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2);
        String key = (String) ops.get("dbkey").getData().get("key");
        log.info("key = {}", key);
        return template;
    }

}
