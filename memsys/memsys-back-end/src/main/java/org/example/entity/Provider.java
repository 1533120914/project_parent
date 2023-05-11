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
 * 
 * </p>
 *
 * @author 徐晨童
 * @since 2023-05-07
 */
@Getter
@Setter
public class Provider implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 供应商名称
     */
    private String providerName;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 供应商号码
     */
    private String tel;

    /**
     * 供应商邮箱
     */
    private String email;

    /**
     * 供应商开户银行名称
     */
    private String bankName;

    /**
     * 供应商开户银行账号
     */
    private String bankAccount;

    /**
     * 供应商地址
     */
    private String address;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建日期
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 修改日期
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifyDate;


}
