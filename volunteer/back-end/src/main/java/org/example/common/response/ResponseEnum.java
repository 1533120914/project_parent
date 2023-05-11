package org.example.common.response;

/**
 * enum用于定义Java枚举类型
 */
public enum ResponseEnum {
    // 所有枚举出来的对象从第一行开始写
    // 默认是public final static修饰
    SUCCESS("000000", "操作成功!"),
    DB_ERROR("000001", "数据库异常!"),
    SERVER_ERROR("000002", "服务器异常"),
    USER_NOT_EXIST("000003", "用户不存在!"),
    PASSWORD_INVALID("000004", "密码错误!"),
    OLD_PASSWORD_INVALID("000004", "原密码错误!"),
    ACCOUNT_LOCKED("000004", "账户已被封!"),
    TOKEN_INVALID("000005", "Token失效,3秒后自动跳转到登录页");

    private String code;
    private String msg;

    /**
     * 枚举类型的构造方法默认是private私有的
     * 目的:阻止外部调用构造方法创建对象, 只允许内部自己创建对象
     */
    ResponseEnum (String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
