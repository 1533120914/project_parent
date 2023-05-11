package org.example.service;

import org.example.common.response.ResponseData;
import org.example.entity.Account;

public interface AccountService {
    ResponseData modifyPassword(Account account,String oldPassword,String newPassword);
}
