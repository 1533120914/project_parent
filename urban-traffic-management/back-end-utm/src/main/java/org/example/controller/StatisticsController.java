package org.example.controller;

import org.example.common.response.ResponseData;
import org.example.entity.view.UserPublishCount;
import org.example.entity.view.WorkerApplyCount;
import org.example.mapper.UserPublishCountMapper;
import org.example.mapper.WorkerPublishCountMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Resource
    UserPublishCountMapper userPublishCountMapper;

    @Resource
    WorkerPublishCountMapper workerPublishCountMapper;

    @GetMapping("/user-publish-count")
    public ResponseData userPublishCount() {
        List<UserPublishCount> list = userPublishCountMapper.selectList(null);
        return ResponseData.success(list);
    }
    @GetMapping("/worker-apply-count")
    public ResponseData workerApplyCount() {
        List<WorkerApplyCount> list = workerPublishCountMapper.selectList(null);
        return ResponseData.success(list);
    }
}
