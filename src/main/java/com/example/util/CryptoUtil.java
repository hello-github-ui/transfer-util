package com.example.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author: Lorem Moon
 * @Date: 2022/6/29 11:03
 * @Document: https://stackabuse.com/encoding-and-decoding-base64-strings-in-java/
 * 加解密工具类: Base64 is a binary-to-text encoding scheme.
 */
public class CryptoUtil {

    /***加密字符串***/
    public static String encode(String str) {
        // 使用 Base64 编码器来编码，解码
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedStr = encoder.encodeToString(str.getBytes(StandardCharsets.UTF_8));
        return encodedStr;
    }

    /***解密字符串***/
    public static String decode(String str) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(str.getBytes(StandardCharsets.UTF_8));
        // byte[] array to String https://howtodoinjava.com/java/array/convert-byte-array-string/
        return new String(decode);
    }
}
