package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.common.exception.CustomException;
import org.example.common.response.ResponseData;
import org.example.common.response.ResponseEnum;
import org.example.entity.Account;
import org.example.entity.query.AccountParam;
import org.example.mapper.AccountMapper;
import org.example.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 账户表 前端控制器
 * </p>
 *
 * @author 黄嘉诚
 * @since 2023-05-06
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountMapper accountMapper;

    @Resource
    AccountService accountService;

    @PostMapping("/login")
    public ResponseData login(Account account) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("username", account.getUsername());
        Account one = accountMapper.selectOne(qw);
        if( one == null || one.getIdentity() != account.getIdentity()) {
            throw new CustomException(ResponseEnum.USER_NOT_EXIST);
        } else if ( one.getState() == 1) {
            throw new CustomException(ResponseEnum.ACCOUNT_LOCKED);
        } else if ( !one.getPassword().equals(account.getPassword())) {
            throw new CustomException(ResponseEnum.PASSWORD_INVALID);
        } else {
            return ResponseData.success(one);
        }
    }

    @PostMapping("/modify-password")
    public ResponseData modifyPassword(Account account, String oldPassword, String newPassword) {
        return accountService.modifyPassword(account,oldPassword,newPassword);
    }

    @PostMapping("/lock")
    public ResponseData lock(Account account) {
        accountMapper.updateById(account);
        return ResponseData.success();
    }

    @GetMapping("/query")
    public ResponseData query(AccountParam param) {
        QueryWrapper qw = new QueryWrapper();
        qw.like(StringUtils.isNotEmpty(param.getUsername()),"username",param.getUsername());
        qw.eq(param.getIdentity()!=null, "identity", param.getIdentity());
        accountMapper.selectPage(param,qw);
        return ResponseData.success(param);
    }
}
