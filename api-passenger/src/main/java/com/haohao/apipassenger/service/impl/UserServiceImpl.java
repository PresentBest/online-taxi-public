package com.haohao.apipassenger.service.impl;

import com.haohao.apipassenger.remote.ServicePassengerUserClient;
import com.haohao.apipassenger.service.IUserService;
import com.haohao.internalcommon.dto.PassengerUserDto;
import com.haohao.internalcommon.dto.VerifiCodeDto;
import com.haohao.internalcommon.result.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    ServicePassengerUserClient servicePassengerUserClient;

    @Override
    public ResultWrapper getUser(String phone) {
        VerifiCodeDto dto = new VerifiCodeDto();
        dto.setPassengerPhone(phone);
        // 根据手机号查询用户信息
        ResultWrapper<PassengerUserDto> userByPhone = servicePassengerUserClient.getUserByPhone(dto);
        return ResultWrapper.success(userByPhone.getData());
    }
}
