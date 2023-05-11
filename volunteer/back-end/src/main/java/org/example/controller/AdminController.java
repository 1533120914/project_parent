package org.example.controller;

import org.example.common.response.ResponseData;
import org.example.mapper.AdminMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author 许婉
 * @since 2023-05-06
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminMapper adminMapper;

}
