package com.haohao.serviceprice.service;

import com.haohao.internalcommon.dto.ForecastPriceDto;
import com.haohao.internalcommon.response.DirectionResponse;
import com.haohao.internalcommon.response.ForecastPriceResponse;
import com.haohao.internalcommon.result.ResultWrapper;
import com.haohao.serviceprice.remote.ServiceMapClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceService {
    @Autowired
    private ServiceMapClient serviceMapClient;

    public ResultWrapper forecastPrice(ForecastPriceDto dto) {
        log.info("调用地图服务查询距离和时长");
        ResultWrapper<DirectionResponse> driving = serviceMapClient.driving(dto);
        DirectionResponse data = driving.getData();
        
        log.info("读取计价规则");
        log.info("根据距离、时长以及计价规则来计算价格");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(24.23);
        return ResultWrapper.success(forecastPriceResponse);
    }
}
