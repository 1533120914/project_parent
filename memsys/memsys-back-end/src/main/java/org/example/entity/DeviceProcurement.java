package org.example.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("device_procurement")
public class DeviceProcurement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer buyerId;

    private Integer deviceTypeId;

    private Integer providerId;

    private Integer quantity;

    private Integer state;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;


}
