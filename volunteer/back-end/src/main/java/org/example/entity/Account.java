package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 映射前端登录的请求参数的实体类
 */
@Data
public class Account {
    @TableId(type = IdType.AUTO)
    private Integer id; // 用户id
    private String username; // 用户名
    private String password; // 密码
    @TableField(exist = false)
    private Integer identity; // 身份
    private String avatar; // 头像
    private Integer state;
}
