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
 * 志愿者表
 * </p>
 *
 * @author 许婉
 * @since 2023-05-06
 */
@Getter
@Setter
public class Volunteer extends Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sex;

    private String mobile;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime modifyDate;


}
