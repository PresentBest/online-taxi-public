package com.haohao.servicemap.remote;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.haohao.internalcommon.constant.AMapConfigConstants;
import com.haohao.internalcommon.response.DirectionResponse;
import com.haohao.internalcommon.result.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class MapDirectionClient {

    @Value("${amap.key}")
    private String amapKey;

    @Autowired
    RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        //组装远程请求路径
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append(AMapConfigConstants.DIRECTION_URL);
        urlBuild.append("?");
        urlBuild.append("origin="+depLongitude+","+depLatitude);
        urlBuild.append("&");
        urlBuild.append("destination="+destLongitude+","+destLatitude);
        urlBuild.append("&");
        urlBuild.append("extensions=base");
        urlBuild.append("&");
        urlBuild.append("output=json");
        urlBuild.append("&");
        urlBuild.append("key="+amapKey);
        log.info(urlBuild.toString());

        //调用高德服务
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
        String body = directionEntity.getBody();
        //解析
        DirectionResponse directionResponse = parseDirectionEntity(body);
        return directionResponse;
    }

    private DirectionResponse parseDirectionEntity(String body){
        DirectionResponse directionResponse = null;
        try{
            //最外层
            JSONObject result = JSONObject.parseObject(body);
            if(result.containsKey(AMapConfigConstants.STATUS)){
                Integer status = result.getInteger(AMapConfigConstants.STATUS);
                if(status==1){
                    if(result.containsKey(AMapConfigConstants.ROUTE)){
                        JSONObject routeJson = result.getJSONObject(AMapConfigConstants.ROUTE);
                        JSONArray pathsArray = routeJson.getJSONArray(AMapConfigConstants.ROUTE_PATHS);
                        JSONObject pathsObject_0 = pathsArray.getJSONObject(0);
                        directionResponse = new DirectionResponse();
                        if(pathsObject_0.containsKey(AMapConfigConstants.ROUTE_PATHS_DISTANCE)){
                            Integer distance = pathsObject_0.getInteger(AMapConfigConstants.ROUTE_PATHS_DISTANCE);
                            directionResponse.setDistance(distance);
                        }

                        if(pathsObject_0.containsKey(AMapConfigConstants.ROUTE_PATHS_DURATION)){
                            Integer duration = pathsObject_0.getInteger(AMapConfigConstants.ROUTE_PATHS_DURATION);
                            directionResponse.setDuration(duration);
                        }
                    }
                }

            }
        }catch (Exception e){

        }
        return directionResponse;
    }
}
