package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.example.common.response.ResponseData;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author 许婉
 * @since 2023-05-06
 */
@Getter
@Setter
public class Admin extends Account implements Serializable {

    private static final long serialVersionUID = 1L;

}
