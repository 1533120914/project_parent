package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.common.response.ResponseData;
import org.example.entity.query.VolunteerParam;
import org.example.mapper.VolunteerMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 志愿者表 前端控制器
 * </p>
 *
 * @author 许婉
 * @since 2023-05-06
 */
@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

    @Resource
    VolunteerMapper volunteerMapper;

    @GetMapping("/query")
    public ResponseData query(VolunteerParam param) {
        QueryWrapper qw = new QueryWrapper();
        qw.select("id", "username", "avatar", "sex", "mobile", "state");
        qw.like(StringUtils.isNotEmpty(param.getUsername()), "username", param.getUsername());
        qw.eq(StringUtils.isNotEmpty(param.getMobile()), "mobile", param.getMobile());
        qw.orderByDesc("create_date");
        volunteerMapper.selectPage(param, qw);
        return ResponseData.success(param);
    }
}
