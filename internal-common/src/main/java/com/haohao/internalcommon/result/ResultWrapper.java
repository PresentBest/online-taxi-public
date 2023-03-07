package com.haohao.internalcommon.result;

import com.haohao.internalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultWrapper<T> {

    private Integer code;

    private String message;

    private T data;

    /**
     * 返回成功包装类
     * @param <T>
     * @return
     */
    public static <T> ResultWrapper success(){
        return new ResultWrapper().setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getMessage());
    }

    /**
     * 返回成功包装类
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultWrapper success(T data){
        return new ResultWrapper().setCode(CommonStatusEnum.SUCCESS.getCode())
                .setMessage(CommonStatusEnum.SUCCESS.getMessage())
                .setData(data);
    }

    /**
     * 返回失败包装类
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultWrapper fail(T data){
        return new ResultWrapper().setCode(CommonStatusEnum.FAIL.getCode())
                .setMessage(CommonStatusEnum.FAIL.getMessage())
                .setData(data);
    }

    /**
     * 返回失败包装类：自定义错误码和返回信息
     * @param code
     * @param message
     * @return
     */
    public static ResultWrapper fail(Integer code, String message){
        return new ResultWrapper().setCode(code)
                .setMessage(message);
    }

    /**
     * 返回失败包装类：自定义错误码、返回信息、具体错误
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static ResultWrapper fail(Integer code, String message, String data){
        return new ResultWrapper().setCode(code)
                .setMessage(message)
                .setData(data);
    }

}
