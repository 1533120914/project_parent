package org.example.controller;

import org.example.common.response.ResponseData;
import org.example.entity.AccuseType;
import org.example.mapper.AccuseTypeMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 投诉类型表 前端控制器
 * </p>
 *
 * @author 黄嘉诚
 * @since 2023-05-06
 */
@RestController
@RequestMapping("/accuse-type")
public class AccuseTypeController {

    @Resource
    AccuseTypeMapper accuseTypeMapper;

    @GetMapping("/list")
    public ResponseData list() {
        List<AccuseType> list = accuseTypeMapper.selectList(null);
        return ResponseData.success(list);
    }

    @PostMapping("/save")
    public ResponseData save(AccuseType accuseType) {
        accuseTypeMapper.insert(accuseType);
        return ResponseData.success();
    }

}
