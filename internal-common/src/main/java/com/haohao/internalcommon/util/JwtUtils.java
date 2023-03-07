package com.haohao.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.haohao.internalcommon.constant.TokenTypeConstants;
import com.haohao.internalcommon.result.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    //sign 盐
    private static final String SIGN = "wuhao@666";

    private static final String JWT_KEY_PHONE = "phone";

    //身份标识
    private static final String JWT_KEY_IDENTITY = "identity";

    //生成token的类型 access|refresh
    private static final String JWT_TOKEN_TYPE = "tokenType";

    private static final String JWT_TOKEN_TIME = "tokenTime";

    //生成token
    public static String generateToken(String passengerPhone, String identity, String tokenType){
        Map<String,String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_TOKEN_TYPE, tokenType);
        // 防止每次生成的token一样。
        map.put(JWT_TOKEN_TIME, Calendar.getInstance().getTime().toString());

        //token过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,1); //设置1天过期
        Date time = instance.getTime();

        JWTCreator.Builder builder = JWT.create();
        //整合map，将map放入builder中
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        //整合过期时间 --- 一般使用redis存储和控制过期时间
//        builder.withExpiresAt(time);

        //生成token
        String token = builder.sign(Algorithm.HMAC256(SIGN));
        return token;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        String phone = "16634441998";
        String identity = "1";
        String s = generateToken(phone, identity, TokenTypeConstants.REFRESH_TOKEN_TYPE);
        System.out.println("生成的token：" + s);
        System.out.println("解析token的值：" + decodeToken(s).toString());
    }


    //解析token
    public static TokenResult decodeToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();
        String tokenType = verify.getClaim(JWT_TOKEN_TYPE).asString();
        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        tokenResult.setTokenType(tokenType);
        return tokenResult;
    }

    /**
     * 校验token，主要判断token是否异常
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token){
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.decodeToken(token);
        }catch (Exception e){

        }
        return tokenResult;
    }
}
