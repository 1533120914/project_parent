package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("device_type")
public class DeviceType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备类型id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 设备类型名称
     */
    private String name;

    private Integer buyerId;

    private String buyerName;

    private Integer managerId;

    private String managerName;

}
