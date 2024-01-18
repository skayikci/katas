package com.katas.jwtlogin;

import com.katas.jwtlogin.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class SpringSecurityLoginJwtApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityLoginJwtApplication.class, args);
    }

}
