package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.response.ResponseData;
import org.example.entity.Dept;
import org.example.entity.view.DeptView;
import org.example.mapper.AccountMapper;
import org.example.mapper.DeptMapper;
import org.example.mapper.DeptViewMapper;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/dept")
public class DeptController {

    @Resource
    DeptMapper deptMapper;
    @Resource
    AccountMapper accountMapper;
    @Resource
    DeptViewMapper deptViewMapper;

    @GetMapping("/list")
    public ResponseData list() {
        QueryWrapper qw = new QueryWrapper();
        qw.select("id","name");
        List list = deptMapper.selectList(qw);
        return ResponseData.success(list);
    }
    @GetMapping("/view")
    public ResponseData view() {
        List<DeptView> list = deptViewMapper.selectList(null);
        return ResponseData.success(list);
    }
    @GetMapping("detail/{id}")
    public ResponseData detail(@PathVariable("id") Integer id) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("dept_id", id);
        DeptView deptView = deptViewMapper.selectOne(qw);
        List list = accountMapper.selectList(qw);
        Map<String, Object> map = new HashMap<>();
        map.put("dept", deptView);
        map.put("employeeList", list);
        return ResponseData.success(map);
    }

    @PostMapping("/save")
    public ResponseData save(Dept dept) {
        deptMapper.insert(dept);
        return ResponseData.success();
    }

}
