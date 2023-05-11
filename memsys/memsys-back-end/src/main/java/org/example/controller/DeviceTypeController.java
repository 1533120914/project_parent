package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.response.ResponseData;
import org.example.entity.DeviceType;
import org.example.mapper.DeviceTypeMapper;
import org.example.service.IDeviceTypeService;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/device-type")
public class DeviceTypeController {

    @Resource
    DeviceTypeMapper deviceTypeMapper;

    @Resource
    IDeviceTypeService deviceTypeService;

    @GetMapping("/list")
    public ResponseData list() {
        return ResponseData.success(deviceTypeMapper.selectList(null));
    }

    @PostMapping("/save")
    public ResponseData save(DeviceType deviceType) {
        deviceTypeService.saveOrUpdate(deviceType);
        return ResponseData.success();
    }

    @GetMapping("/detail/{id}")
    public ResponseData detail(@PathVariable("id") Integer id) {
        DeviceType deviceType = deviceTypeMapper.selectById(id);
        return ResponseData.success(deviceType);
    }

    @GetMapping("/exists")
    public ResponseData exists(DeviceType deviceType){
        QueryWrapper qw = new QueryWrapper();
        qw.eq("id", deviceType.getId());
        qw.eq("buyer_id", deviceType.getBuyerId());
        return ResponseData.success(deviceTypeMapper.exists(qw)?"success":"error");
    }
}
