package net.hwyz.iov.cloud.edd.dictionary.service;


import lombok.extern.slf4j.Slf4j;
import net.hwyz.iov.cloud.framework.security.annotation.EnableCustomConfig;
import net.hwyz.iov.cloud.framework.security.annotation.EnableCustomFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author hwyz_leo
 */
@Slf4j
@EnableCustomConfig
@SpringBootApplication
@EnableDiscoveryClient
@EnableCustomFeignClients
public class DictionaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictionaryApplication.class, args);
        log.info("应用启动完成");
    }

}


