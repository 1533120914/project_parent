package org.example.controller;

import org.example.common.response.ResponseData;
import org.example.entity.view.TaskApplyCountView;
import org.example.entity.view.TaskCountView;
import org.example.mapper.TaskApplyCountViewMapper;
import org.example.mapper.TaskCountViewMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Resource
    TaskCountViewMapper taskCountViewMapper;

    @Resource
    TaskApplyCountViewMapper taskApplyCountViewMapper;

    @GetMapping("/task-count")
    public ResponseData taskCount() {
        List<TaskCountView> list = taskCountViewMapper.selectList(null);
        return ResponseData.success(list);
    }
    @GetMapping("/task-apply-count")
    public ResponseData taskApplyCount() {
        List<TaskApplyCountView> list = taskApplyCountViewMapper.selectList(null);
        return ResponseData.success(list);
    }
}
