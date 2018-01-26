/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Třída pro zabezpečení aplikace
 * 
 * @author Jirka_
 */
public class Crypto {
    
    /**
     * Metoda pro hešování textu
     * 
     * @param text
     * @return hešovaný string
     */
    public static String hashSHA256(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(
            text.getBytes(StandardCharsets.UTF_8));

            return bytesToHex(encodedhash);
        }
        catch (Exception e) {
            return text;
        }
        
    }
    
    /**
     * Pomocná metoda pro hešování textu
     * 
     * @param hash
     */
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
}
