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
 * 任务类型表
 * </p>
 *
 * @author 许婉
 * @since 2023-05-06
 */
@Getter
@Setter
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer typeId;

    private String typeName;

    /**
     * 任务标题
     */
    private String title;

    /**
     * 任务描述
     */
    private String description;

    private Integer volunteerId;

    private String volunteerName;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDelete;
    @TableField(fill = FieldFill.INSERT)
    private Integer state;

    private Integer createBy;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifyDate;


}
