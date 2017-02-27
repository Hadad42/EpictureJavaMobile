package com.java.demers_j.epicture.FlickrApi;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Hadad on 25/02/2017.
 */

public class OauthHandler {
    public static String generate_timestamp() {
        long cur = System.currentTimeMillis() / 1000;
        return Long.toString(cur);
    }


    public static String encode_hmacSha1(String value, String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secret = new SecretKeySpec(key.getBytes(), mac.getAlgorithm());
        mac.init(secret);
        byte[] bytes = mac.doFinal(value.getBytes());
        byte[] res = Base64.encode(bytes, Base64.DEFAULT);
        Log.d("key hmac is ", key);
        return new String(res);
    }
}
