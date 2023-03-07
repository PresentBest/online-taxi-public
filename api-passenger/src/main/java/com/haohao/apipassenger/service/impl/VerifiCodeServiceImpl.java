package com.haohao.apipassenger.service.impl;

import com.haohao.apipassenger.remote.ServicePassengerUserClient;
import com.haohao.apipassenger.remote.ServiceVerifiCodeClient;
import com.haohao.apipassenger.service.IVerifiCodeService;
import com.haohao.internalcommon.constant.CommonStatusEnum;
import com.haohao.internalcommon.constant.IdentityConstants;
import com.haohao.internalcommon.constant.TokenTypeConstants;
import com.haohao.internalcommon.dto.VerifiCodeDto;
import com.haohao.internalcommon.response.NumberCodeResponse;
import com.haohao.internalcommon.response.TokenResponse;
import com.haohao.internalcommon.result.ResultWrapper;
import com.haohao.internalcommon.util.JwtUtils;
import com.haohao.internalcommon.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

@Service
@Slf4j
public class VerifiCodeServiceImpl implements IVerifiCodeService {

    @Autowired
    private ServiceVerifiCodeClient serviceVerifiCodeClient;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    //设计的k-v是String类型的话 推荐使用 否则使用RedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public ResultWrapper verificationCode(String passengerPhone) {
        //调用验证码服务，获取验证码
        log.info("调用验证码服务，获取验证码");
        ResultWrapper<NumberCodeResponse>  numberCode= serviceVerifiCodeClient.getNumberCode(6);
        Integer code = numberCode.getData().getNumberCode();
        log.info("调用验证码服务器获得的验证码为:" + code);
        //redis
        log.info("将验证码存入redis缓存");
        //设置key、value、过期时间
        String key = RedisPrefixUtils.generateKeyByPhone(passengerPhone);
        stringRedisTemplate.opsForValue().set(key, code+"", 2, TimeUnit.MINUTES);
        //通过短信服务商 将短信发送到用户手机上
        return ResultWrapper.success();
    }

    @Override
    public ResultWrapper verificationCodeCheck(VerifiCodeDto dto) {
        //根据手机号去redis读取验证码
        String key = RedisPrefixUtils.generateKeyByPhone(dto.getPassengerPhone());
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        log.info("Redis取得验证码为 {}", codeRedis);
        //校验验证码
        if(StringUtils.isBlank(codeRedis)){
            return ResultWrapper.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),
                    CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }
        if(!dto.getVerificationCode().trim().equals(codeRedis.trim())){
            return ResultWrapper.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),
                    CommonStatusEnum.VERIFICATION_CODE_ERROR.getMessage());
        }

        //判断原来是否有用户，并进行对应的处理
        servicePassengerUserClient.loginOrReg(dto);
        //颁发令牌，不应该用魔法值，应该用枚举或者常量
        String accessToken = JwtUtils.generateToken(dto.getPassengerPhone(), IdentityConstants.PASSENGER_IDENTITY, TokenTypeConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generateToken(dto.getPassengerPhone(), IdentityConstants.PASSENGER_IDENTITY, TokenTypeConstants.ACCESS_TOKEN_TYPE);

        //将token存储入redis中
        String accessTokenKey = RedisPrefixUtils.generateTokenKey(dto.getPassengerPhone(), IdentityConstants.PASSENGER_IDENTITY,TokenTypeConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30,TimeUnit.DAYS);//accessToken，并设置有效期为30天

        String refreshTokenKey = RedisPrefixUtils.generateTokenKey(dto.getPassengerPhone(), IdentityConstants.PASSENGER_IDENTITY,TokenTypeConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,31,TimeUnit.DAYS);//refreshToken，并设置有效期为31天

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResultWrapper.success(tokenResponse);
    }

}
