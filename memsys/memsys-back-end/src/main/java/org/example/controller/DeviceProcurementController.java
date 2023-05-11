package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.response.ResponseData;
import org.example.entity.DeviceProcurement;
import org.example.entity.query.DeviceProcurementViewParam;
import org.example.mapper.DeviceProcurementMapper;
import org.example.mapper.DeviceProcurementViewMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐晨童
 * @since 2023-05-07
 */
@RestController
@RequestMapping("/device-procurement")
public class DeviceProcurementController {

    @Resource
    DeviceProcurementMapper deviceProcurementMapper;

    @Resource
    DeviceProcurementViewMapper deviceProcurementViewMapper;

    @PostMapping("/save")
    public ResponseData save(DeviceProcurement deviceProcurement) {
        deviceProcurementMapper.insert(deviceProcurement);
        return ResponseData.success();
    }

    @GetMapping("/view")
    public ResponseData view(DeviceProcurementViewParam param) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq(param.getProviderId()!= null, "provider_id", param.getProviderId());
        qw.eq(param.getDeviceTypeId()!= null, "device_type_id", param.getDeviceTypeId());
        qw.eq(param.getBuyerId()!= null, "buyer_id", param.getBuyerId());
        qw.eq(param.getState()!= null, "state", param.getState());
        deviceProcurementViewMapper.selectPage(param, qw);
        return ResponseData.success(param);
    }

    @PostMapping("/state")
    public ResponseData state (DeviceProcurement deviceProcurement) {
        deviceProcurementMapper.updateById(deviceProcurement);
        return ResponseData.success();
    }

}
