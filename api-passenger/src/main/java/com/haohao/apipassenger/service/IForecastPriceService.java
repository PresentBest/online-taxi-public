package com.haohao.apipassenger.service;

import com.haohao.internalcommon.result.ResultWrapper;

public interface IForecastPriceService {

    ResultWrapper forecastPrice(String depLongitude, String depLatitude, String destLongitude, String destLatitude);

}
