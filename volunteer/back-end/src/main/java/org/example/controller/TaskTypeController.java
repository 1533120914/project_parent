package org.example.controller;

import org.example.common.response.ResponseData;
import org.example.entity.TaskType;
import org.example.mapper.TaskTypeMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 任务类型表 前端控制器
 * </p>
 *
 * @author 许婉
 * @since 2023-05-06
 */
@RestController
@RequestMapping("/task-type")
public class TaskTypeController {

    @Resource
    TaskTypeMapper taskTypeMapper;

    @GetMapping("/list")
    public ResponseData list() {
        return ResponseData.success(taskTypeMapper.selectList(null));
    }

    @PostMapping("/save")
    public ResponseData save(TaskType taskType) {
        taskTypeMapper.insert(taskType);
        return ResponseData.success();
    }

}
