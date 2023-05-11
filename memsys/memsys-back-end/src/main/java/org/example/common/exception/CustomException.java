package org.example.common.exception;

import org.example.common.response.ResponseEnum;

/**
 * 自定义异常类, 继承运行时异常RuntimeException类
 */
public class CustomException extends RuntimeException{
    /**
     * 使用枚举类型作为成员变量
     */
    private ResponseEnum responseEnum;

    /**
     * 构造方法
     */
    public CustomException(ResponseEnum responseEnum) {
        super(responseEnum.getCode() + ":" + responseEnum.getMsg());
        this.responseEnum = responseEnum;
    }

    public ResponseEnum getResponseEnum() {
        return responseEnum;
    }
}
