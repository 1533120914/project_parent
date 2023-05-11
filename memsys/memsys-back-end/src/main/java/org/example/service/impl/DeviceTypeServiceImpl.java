package org.example.service.impl;

import org.example.entity.DeviceType;
import org.example.mapper.DeviceTypeMapper;
import org.example.service.IDeviceTypeService;
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
public class DeviceTypeServiceImpl extends ServiceImpl<DeviceTypeMapper, DeviceType> implements IDeviceTypeService {

}
