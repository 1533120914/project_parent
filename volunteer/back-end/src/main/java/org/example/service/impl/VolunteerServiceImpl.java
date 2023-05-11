package org.example.service.impl;

import org.example.entity.Volunteer;
import org.example.mapper.VolunteerMapper;
import org.example.service.IVolunteerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 志愿者表 服务实现类
 * </p>
 *
 * @author 许婉
 * @since 2023-05-06
 */
@Service
public class VolunteerServiceImpl extends ServiceImpl<VolunteerMapper, Volunteer> implements IVolunteerService {

}
