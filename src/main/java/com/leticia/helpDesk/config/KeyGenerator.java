package com.leticia.helpDesk.config;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {

    public static void main(String[] args) {
            SecureRandom secureRandom = new SecureRandom();
            byte[] key = new byte[64]; // 512 bits (64 bytes)
            secureRandom.nextBytes(key);

            // Codificar a chave em Base64
            String encodedKey = Base64.getEncoder().encodeToString(key);
            System.out.println("Chave gerada (Base64): " + encodedKey);
        }
    }

