package org.example.service.impl;

import org.example.entity.Accuse;
import org.example.mapper.AccuseMapper;
import org.example.service.IAccuseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 投诉记录表 服务实现类
 * </p>
 *
 * @author 黄嘉诚
 * @since 2023-05-06
 */
@Service
public class AccuseServiceImpl extends ServiceImpl<AccuseMapper, Accuse> implements IAccuseService {

}
