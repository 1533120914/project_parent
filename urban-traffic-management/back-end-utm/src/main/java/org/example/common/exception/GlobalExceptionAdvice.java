package org.example.common.exception;

import org.example.common.response.ResponseData;
import org.example.common.response.ResponseEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 全局异常处理器类,用于处理项目中所有异常
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     *  处理全局的SQLException
     */
    @ExceptionHandler(SQLException.class)
    public ResponseData handleSqlException(SQLException e) {
        e.printStackTrace();
        return ResponseData.error(ResponseEnum.DB_ERROR);
    }
    /**
     *  处理全局的自定义的业务异常
     */
    @ExceptionHandler(CustomException.class)
    public ResponseData handleCustomException(CustomException e) {
        e.printStackTrace();
        return ResponseData.error(e.getResponseEnum());
    }

    /**
     *  处理全局的各种Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseData handleException(Exception e) {
        e.printStackTrace();
        return ResponseData.error(ResponseEnum.SERVER_ERROR);
    }

}
