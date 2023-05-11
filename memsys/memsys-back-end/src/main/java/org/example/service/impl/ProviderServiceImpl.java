package org.example.service.impl;

import org.example.entity.Provider;
import org.example.mapper.ProviderMapper;
import org.example.service.IProviderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐晨童
 * @since 2023-05-07
 */
@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements IProviderService {

}
