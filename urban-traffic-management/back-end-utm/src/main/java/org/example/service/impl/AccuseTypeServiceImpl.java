package org.example.service.impl;

import org.example.entity.AccuseType;
import org.example.mapper.AccuseTypeMapper;
import org.example.service.IAccuseTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 投诉类型表 服务实现类
 * </p>
 *
 * @author 黄嘉诚
 * @since 2023-05-06
 */
@Service
public class AccuseTypeServiceImpl extends ServiceImpl<AccuseTypeMapper, AccuseType> implements IAccuseTypeService {

}
