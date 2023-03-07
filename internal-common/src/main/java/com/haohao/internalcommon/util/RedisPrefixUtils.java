package com.haohao.internalcommon.util;

public class RedisPrefixUtils {

    //乘客验证码的前缀
    public static final String verificationCodePrefix = "paasenger-verification-code-";

    //乘客验证码的前缀
    public static final String tokenPrefix = "token-";

    /**
     * 根据手机号生成存储验证码的key
     * @param phone
     * @return
     */
    public static String generateKeyByPhone(String phone){
        return verificationCodePrefix + phone;
    }

    /**
     * 根据手机号和身份标识生成在redis中存储token的key值
     * @param phone
     * @param identity
     * @return
     */
    public static String generateTokenKey(String phone, String identity, String tokenType){
        return tokenPrefix + phone + "1" + identity + "-" + tokenType;
    }
}
