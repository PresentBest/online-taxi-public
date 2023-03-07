package com.haohao.internalcommon.constant;

public enum CommonStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),

    /**
     * 请求失败
     */
    FAIL(-1, "请求失败"),

    /**
     * 验证码不正确
     */
    VERIFICATION_CODE_ERROR(1099, "验证码不正确"),

    /**
     * Token类提示：1100-1199
     */
    TOKEN_ERROR(1199,"token错误"),

    /**
     * 用户提示： 1200-1299
     */
    USER_NOT_EXISTS(1200,"用户不存在");

    private Integer code;

    private String message;

    CommonStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    CommonStatusEnum() {
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
