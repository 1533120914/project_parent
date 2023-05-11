package org.example.entity.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.example.entity.Provider;

@Data
public class ProviderParam extends Page<Provider> {
    private String providerName;
    private String tel;
    private String linkman;
}
