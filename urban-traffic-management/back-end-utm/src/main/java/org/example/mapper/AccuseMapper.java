package org.example.mapper;

import org.apache.ibatis.annotations.Options;
import org.example.entity.Accuse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 投诉记录表 Mapper 接口
 * </p>
 *
 * @author 黄嘉诚
 * @since 2023-05-06
 */
public interface AccuseMapper extends BaseMapper<Accuse> {

    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(Accuse accuse);

}
