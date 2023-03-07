package com.haohao.servicepassengeruser.service;

import com.haohao.internalcommon.constant.CommonStatusEnum;
import com.haohao.internalcommon.result.ResultWrapper;
import com.haohao.servicepassengeruser.dto.PassengerUser;
import com.haohao.servicepassengeruser.mapper.PassengerUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {
    @Autowired
    PassengerUserMapper passengerUserMapper;

    public ResultWrapper loginOrReg(String passengerPhone) {
        log.info("根据手机号查询/注册用户信息");
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if(passengerUsers.size()==0){//注册用户
            log.info("未查询到用户信息，注册用户");
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName("美杜莎");
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setState((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);
            passengerUserMapper.insert(passengerUser);
        }
        return ResultWrapper.success();
    }

    public ResultWrapper getUserByPhone(String passengerPhone) {
        log.info("getUserByPhone");
        Map<String,Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        if (passengerUsers.size() == 0){
            return ResultWrapper.fail(CommonStatusEnum.USER_NOT_EXISTS.getCode(),CommonStatusEnum.USER_NOT_EXISTS.getMessage());
        } else {
            PassengerUser passengerUser = passengerUsers.get(0);
            return ResultWrapper.success(passengerUser);
        }

    }
}
