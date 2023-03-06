package com.innowireless.web;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

@Slf4j
public class JasyptTest {

    @Test
    void encrypt() {
        StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
        jasypt.setPassword("innoserversw2"); // 암호화 키(password)
        jasypt.setAlgorithm("PBEWithMD5AndDES");

        String encryptedText = jasypt.encrypt("123456"); // 암호화
        String plainText = jasypt.decrypt(encryptedText); // 복호화

        log.info("encryptedText: ENC({})", encryptedText); // 암호화된 값
        log.info("plainText: {}", plainText); // 복호화된 값
    }
}
