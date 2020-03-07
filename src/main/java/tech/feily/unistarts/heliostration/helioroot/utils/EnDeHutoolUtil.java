package tech.feily.unistarts.heliostration.helioroot.utils;

import java.security.PrivateKey;
import java.security.PublicKey;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.DES;

/**
 * The class EnDeHutoolUtil implements common symmetric and asymmetric encryption algorithms.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class EnDeHutoolUtil {
    
    
    /** 
     * Create an RSA instance.
     * 
     * Because RSA is a static instance,
     * its public key is the same in every method call, private key too.
     */
    private static RSA rsa = new RSA();
    
    /**
     * RSA asymmetric encryption.
     * 
     * @param plaintext
     * @return ciphertext.
     */
    public static String rsaEncrypt(String plaintext) {
        if (plaintext == null || plaintext.equals("")) {
            return null;
        }
        return rsa.encryptBase64(plaintext, KeyType.PublicKey);
    }

    /**
     * RSA asymmetric decryption.
     * 
     * @param ciphertext
     * @return plaintext.
     */
    public static String rsaDecrypt(String ciphertext) {
        if (ciphertext == null || ciphertext.equals("")) {
            return null;
        }
        return rsa.decryptStr(ciphertext, KeyType.PrivateKey);
    }
    
    /**
     * DES symmetric encryption.
     * 
     * @param plaintext
     * @param key - encryption key.
     * @return ciphertext.
     */
    public static String desEncrypt(String plaintext, String key) {
        if ((plaintext == null || plaintext.equals("")) || (key == null || key.equals(""))) {
            return null;
        }
        DES des = SecureUtil.des(key.getBytes());
        return des.encryptHex(plaintext);
    }

    /**
     * DES symmetric decryption.
     * 
     * @param ciphertext
     * @param key - decryption key, same with encryption key.
     * @return ciphertext.
     */
    public static String desDecrypt(String ciphertext, String key) {
        if ((ciphertext == null || ciphertext.equals("")) || (key == null || key.equals(""))) {
            return null;
        }
        DES des = SecureUtil.des(key.getBytes());
        return des.decryptStr(ciphertext);
    }
    
    /**
     * Get rsa's public key.
     * @return public key.
     */
    public static PublicKey getPublicKey() {
        return rsa.getPublicKey();
    }
    
    /**
     * Get rsa's private key.
     * @return private key.
     */
    public static PrivateKey getPrivateKey() {
        return rsa.getPrivateKey();
    }
    
}
