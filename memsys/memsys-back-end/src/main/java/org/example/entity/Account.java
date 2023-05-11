package org.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author 徐晨童
 * @since 2023-05-07
 */
@Getter
@Setter
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 身份
     */
    private Integer identity;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号码
     */
    private String mobile;

    private Integer deptId;

    /**
     * 注册日期
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 修改日期
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifyDate;


}
