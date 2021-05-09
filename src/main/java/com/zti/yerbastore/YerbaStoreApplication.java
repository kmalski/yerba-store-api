package com.zti.yerbastore;

import com.zti.yerbastore.security.SecretKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class YerbaStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(YerbaStoreApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartupActions() throws NoSuchAlgorithmException {
        new SecretKeyGenerator().generateSecretKey();
    }

}
