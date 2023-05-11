package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 投诉类型表
 * </p>
 *
 * @author 黄嘉诚
 * @since 2023-05-06
 */
@Getter
@Setter
@TableName("accuse_type")
public class AccuseType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 投诉类型名称
     */
    private String name;


}
