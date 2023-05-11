package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.exception.CustomException;
import org.example.common.response.ResponseData;
import org.example.common.response.ResponseEnum;
import org.example.entity.Account;
import org.example.mapper.AccountMapper;
import org.example.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    AccountMapper accountMapper;

    @Override
    public ResponseData modifyPassword(Account account,String oldPassword,String newPassword) {
        Account one = accountMapper.selectById(account.getId());
        // 先判断原密码是否正确
        if(!one.getPassword().equals(oldPassword)) {
            // 原密码错误直接抛出异常
            throw new CustomException(ResponseEnum.OLD_PASSWORD_INVALID);
        }else {
            account.setPassword(newPassword);
            accountMapper.updateById(account);
        }
        return ResponseData.success();
    }
}
