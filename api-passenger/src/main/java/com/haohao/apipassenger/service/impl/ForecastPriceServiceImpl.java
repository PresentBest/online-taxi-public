package com.haohao.apipassenger.service.impl;

import com.haohao.apipassenger.service.IForecastPriceService;
import com.haohao.internalcommon.response.ForecastPriceResponse;
import com.haohao.internalcommon.result.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ForecastPriceServiceImpl implements IForecastPriceService {

    @Override
    public ResultWrapper forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude) {
        log.info("调用计价服务");
        ForecastPriceResponse forecastPriceResponse = new ForecastPriceResponse();
        forecastPriceResponse.setPrice(24.23);
        return ResultWrapper.success(forecastPriceResponse);
    }

}
