package org.example.controller;

import org.example.common.response.ResponseData;
import org.example.mapper.DeptEmpCountMapper;
import org.example.mapper.ProcurementBuyerCountMapper;
import org.example.mapper.ProcurementDeviceCountMapper;
import org.example.mapper.ProcurementProviderCountMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    ProcurementBuyerCountMapper procurementBuyerCountMapper;
    @Resource
    ProcurementProviderCountMapper procurementProviderCountMapper;
    @Resource
    ProcurementDeviceCountMapper procurementDeviceCountMapper;
    @Resource
    DeptEmpCountMapper deptEmpCountMapper;

    @GetMapping("/s1")
    public ResponseData s1() {
        return ResponseData.success(procurementBuyerCountMapper.selectList(null));
    }
    @GetMapping("/s2")
    public ResponseData s2() {
        return ResponseData.success(procurementProviderCountMapper.selectList(null));
    }
    @GetMapping("/s3")
    public ResponseData s3() {
        return ResponseData.success(procurementDeviceCountMapper.selectList(null));
    }
    @GetMapping("/s4")
    public ResponseData s4() {
        return ResponseData.success(deptEmpCountMapper.selectList(null));
    }
}
