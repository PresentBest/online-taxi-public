package com.haohao.servicemap.service;

import com.haohao.internalcommon.response.DirectionResponse;
import com.haohao.internalcommon.result.ResultWrapper;
import com.haohao.servicemap.remote.MapDirectionClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DirectionService {

    @Autowired
    MapDirectionClient mapDirectionClient;

    public ResultWrapper driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        DirectionResponse directionResponse = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);
        return ResultWrapper.success(directionResponse);
    }

}
