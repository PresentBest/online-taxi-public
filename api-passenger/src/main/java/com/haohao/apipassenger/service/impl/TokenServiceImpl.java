package com.haohao.apipassenger.service.impl;

import com.haohao.apipassenger.service.ITokenService;
import com.haohao.internalcommon.constant.CommonStatusEnum;
import com.haohao.internalcommon.constant.TokenTypeConstants;
import com.haohao.internalcommon.response.TokenResponse;
import com.haohao.internalcommon.result.ResultWrapper;
import com.haohao.internalcommon.result.TokenResult;
import com.haohao.internalcommon.util.JwtUtils;
import com.haohao.internalcommon.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultWrapper refreshToken(String refreshTokenSrc) {
        // 解析 refreshToken
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if (tokenResult == null){
            return ResultWrapper.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getMessage());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();

        // 去读取redis中 的refreshToken
        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(phone,identity, TokenTypeConstants.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);

        // 校验refreshToken
        if ((StringUtils.isBlank(refreshTokenRedis))  || (!refreshTokenSrc.trim().equals(refreshTokenRedis.trim()))){
            return ResultWrapper.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getMessage());
        }

        // 生成双token
        String refreshToken = JwtUtils.generateToken(phone,identity,TokenTypeConstants.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generateToken(phone,identity,TokenTypeConstants.ACCESS_TOKEN_TYPE);

        String accessTokenKey = RedisPrefixUtils.generateTokenKey(phone,identity,TokenTypeConstants.ACCESS_TOKEN_TYPE);

        stringRedisTemplate.opsForValue().set(accessTokenKey , accessToken , 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey , refreshToken , 31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);

        return ResultWrapper.success(tokenResponse);
    }
}
