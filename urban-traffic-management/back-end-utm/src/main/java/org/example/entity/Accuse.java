package org.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 投诉记录表
 * </p>
 *
 * @author 黄嘉诚
 * @since 2023-05-06
 */
@Getter
@Setter
public class Accuse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 投诉类型id
     */
    private Integer typeId;

    /**
     * 投诉类型名称
     */
    private String typeName;

    /**
     * 投诉标题
     */
    private String title;

    /**
     * 投诉内容描述
     */
    private String description;

    /**
     * 投诉回复
     */
    private String reply;

    private Integer userId;

    private String userName;

    private Integer workerId;

    private String workerName;

    @TableLogic
    private Integer isDelete;

    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifyDate;


}
