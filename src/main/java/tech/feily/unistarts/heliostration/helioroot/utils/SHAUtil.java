package tech.feily.unistarts.heliostration.helioroot.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * This class gets the sha256 hash value of the input string.
 * 
 * @author Feily Zhang
 * @version v0.1
 */
public class SHAUtil {

    /**
     * Using Apache commons codec library to realize.
     * 
     * @param origin - the input string.
     * @return the sha256 hash value.
     */
    public static String getSHA256BasedMD(String origin) {
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(origin.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    /**
     * Using Hutool library to realize.
     * 
     * @param origin - the input string.
     * @return the sha256 hash value.
     */
    public static String sha256BasedHutool(String origin) {
        return DigestUtil.sha256Hex(origin);
    }
    
}
