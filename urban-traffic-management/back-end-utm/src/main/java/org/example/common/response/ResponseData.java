package org.example.common.response;

/**
 * 这是统一封装的返回结构
 */
public class ResponseData {
    /**
     * 结果码
     */
    private String code;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public static ResponseData success() {
        return success(null);
    }
    public static ResponseData success(Object data) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(ResponseEnum.SUCCESS.getCode());
        responseData.setMsg(ResponseEnum.SUCCESS.getMsg());
        responseData.setData(data);
        return responseData;
    }
    public static ResponseData error(ResponseEnum responseEnum) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(responseEnum.getCode());
        responseData.setMsg(responseEnum.getMsg());
        return responseData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
