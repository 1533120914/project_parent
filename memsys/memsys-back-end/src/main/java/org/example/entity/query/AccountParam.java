package org.example.entity.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.example.entity.Account;

@Data
public class AccountParam extends Page<Account> {
    private String username;
    private Integer identity;
}
