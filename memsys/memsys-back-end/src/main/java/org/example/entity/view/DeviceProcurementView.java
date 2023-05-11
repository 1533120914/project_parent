package org.example.entity.view;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author 徐晨童
 * @since 2023-05-08
 */
@Getter
@Setter
@TableName("device_procurement_view")
public class DeviceProcurementView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户名
     */
    private Integer buyerId;
    private String buyerName;

    private Integer deviceTypeId;

    /**
     * 设备类型名称
     */
    private String deviceName;

    /**
     * 供应商名称
     */
    private Integer providerId;
    private String providerName;

    private Integer quantity;

    private Double price;

    private Integer state;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;


}
