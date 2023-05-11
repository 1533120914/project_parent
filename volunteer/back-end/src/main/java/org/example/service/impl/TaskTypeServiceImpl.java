package org.example.service.impl;

import org.example.entity.TaskType;
import org.example.mapper.TaskTypeMapper;
import org.example.service.ITaskTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务类型表 服务实现类
 * </p>
 *
 * @author 许婉
 * @since 2023-05-06
 */
@Service
public class TaskTypeServiceImpl extends ServiceImpl<TaskTypeMapper, TaskType> implements ITaskTypeService {

}
