package org.example.controller;

import org.example.common.response.ResponseData;
import org.example.entity.Account;
import org.example.entity.Volunteer;
import org.example.mapper.VolunteerMapper;
import org.example.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Resource
    AccountService accountService;

    @Resource
    VolunteerMapper volunteerMapper;

    @PostMapping("/login")
    public ResponseData login(Account account) {
        return accountService.login(account);
    }

    /**
     *
     * @param account 接收用户id和identity
     * @param oldPassword 接收原密码
     * @param newPassword 接收新密码
     * @return
     */
    @PostMapping("/modify-password")
    public ResponseData modifyPassword(Account account, String oldPassword, String newPassword) {
        return accountService.modifyPassword(account,oldPassword,newPassword);
    }

    @GetMapping("/personal")
    public ResponseData personal(Account account) {
        return accountService.personal(account);
    }

    @PostMapping("/lock")
    public ResponseData lock(Volunteer volunteer) {
        volunteerMapper.updateById(volunteer);
        return ResponseData.success();
    }
}
