package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.common.response.ResponseData;
import org.example.entity.Provider;
import org.example.entity.query.ProviderParam;
import org.example.mapper.ProviderDeviceMapper;
import org.example.mapper.ProviderMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐晨童
 * @since 2023-05-07
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {
    @Resource
    ProviderMapper providerMapper;

    @Resource
    ProviderDeviceMapper providerDeviceMapper;

    @GetMapping("/query")
    public ResponseData query(ProviderParam param) {
        QueryWrapper qw = new QueryWrapper();
        qw.like(StringUtils.isNotEmpty(param.getProviderName()), "provider_name", param.getProviderName());
        qw.eq(StringUtils.isNotEmpty(param.getTel()), "tel", param.getTel());
        qw.eq(StringUtils.isNotEmpty(param.getLinkman()), "linkman", param.getLinkman());
        providerMapper.selectPage(param, qw);
        return ResponseData.success(param);
    }

    @GetMapping("/detail/{id}")
    public ResponseData detail(@PathVariable("id") Integer providerId, Integer deviceId) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq(providerId!=null,"provider_id", providerId);
        qw.eq(deviceId!=null,"device_id", deviceId);
        Provider provider = providerMapper.selectById(providerId);
        List list = providerDeviceMapper.selectList(qw);
        Map<String, Object> map = new HashMap();
        map.put("provider", provider);
        map.put("list", list);
        return ResponseData.success(map);
    }

    @GetMapping("/list")
    public ResponseData list() {
        QueryWrapper qw = new QueryWrapper();
        qw.select("id", "provider_name");
        return ResponseData.success(providerMapper.selectList(qw));
    }
}
