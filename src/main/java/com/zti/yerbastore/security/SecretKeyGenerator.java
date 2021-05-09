package com.zti.yerbastore.security;

import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class SecretKeyGenerator {

    private static String secretKey;

    public void generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        secretKey = keyGenerator.generateKey().toString();
    }

    public static byte[] getSecretKeyBytes() {
        return secretKey.getBytes(StandardCharsets.UTF_8);
    }

}
