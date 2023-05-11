package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.common.response.ResponseData;
import org.example.common.util.UUIDUtil;
import org.example.entity.Task;
import org.example.entity.TaskPicture;
import org.example.entity.query.TaskParam;
import org.example.mapper.TaskMapper;
import org.example.mapper.TaskPictureMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 任务类型表 前端控制器
 * </p>
 *
 * @author 许婉
 * @since 2023-05-06
 */
@RestController
@RequestMapping("/task")
public class TaskController {
    @Resource
    TaskMapper taskMapper;
    @Resource
    TaskPictureMapper taskPictureMapper;

    @Value("${task.picture.save.path}")
    private String taskPictureSavePath;

    @GetMapping("/query")
    public ResponseData query (TaskParam param) {
        QueryWrapper qw = new QueryWrapper();
        qw.select("id", "type_name", "title", "state");
        qw.like(StringUtils.isNotEmpty(param.getTitle()), "title", param.getTitle());
        qw.eq(param.getTypeId()!= null, "type_id", param.getTypeId());
        qw.eq(param.getVolunteerId()!= null, "volunteer_id", param.getVolunteerId());
        qw.eq(param.getCreateBy()!= null, "create_by", param.getCreateBy());
        qw.eq(param.getState()!= null, "state", param.getState());
        qw.ge(param.getHighDate()!=null && param.getHighDate()==null, "create_date", param.getHighDate());
        qw.le(param.getLowDate()==null && param.getLowDate()!=null, "create_date", param.getLowDate());
        qw.between(param.getLowDate()!=null && param.getHighDate()!= null, "create_date", param.getLowDate(),param.getHighDate());
        qw.orderByDesc("create_date");
        taskMapper.selectPage(param, qw);
        return ResponseData.success(param);
    }

    @GetMapping("/del/{id}")
    public ResponseData del(@PathVariable("id") Integer id) {
        taskMapper.deleteById(id);
        return ResponseData.success();
    }

    @PostMapping("/apply")
    public ResponseData apply(Task task) {
        task.setState(1);
        taskMapper.updateById(task);
        return ResponseData.success();
    }

    @PostMapping("/save")
    public ResponseData save(Task task) {
        taskMapper.insert(task);
        return ResponseData.success();
    }


    @PostMapping("/finish/{id}")
    public ResponseData finish(@PathVariable("id") Integer id) {
        Task task = new Task();
        task.setId(id);
        task.setState(2);
        taskMapper.updateById(task);
        return ResponseData.success();
    }

    @GetMapping("/detail/{id}")
    public ResponseData detail(@PathVariable("id") Integer id) {
        Task task = taskMapper.selectById(id);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("task_id", id);
        List list = taskPictureMapper.selectList(qw);
        Map<String, Object> map = new HashMap<>();
        map.put("task", task);
        map.put("pictureList", list);
        return ResponseData.success(map);
    }

    @GetMapping("/picture/del/{id}")
    public ResponseData deleteImage(@PathVariable("id") Integer id) {
        taskPictureMapper.deleteById(id);
        return ResponseData.success();
    }

    @PostMapping("/picture/upload")
    public ResponseData saveImage(Integer id, MultipartFile file) throws IOException {
        File folder = new File(taskPictureSavePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String fileName = UUIDUtil.uniqueFileName(file.getOriginalFilename());
        File saveFile = new File(taskPictureSavePath, fileName);
        file.transferTo(saveFile);
        TaskPicture picture = new TaskPicture();
        picture.setTaskId(id);
        picture.setFileName(fileName);
        taskPictureMapper.insert(picture);
        return ResponseData.success(picture);
    }
    @GetMapping("/picture/download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        // 根据文件名下载文件
        OutputStream os = response.getOutputStream();
        File file = new File(taskPictureSavePath, fileName);
        InputStream is = new FileInputStream(file);
        byte[] data = new byte[1024 * 1024];
        int len = -1;
        while ((len = is.read(data)) != -1) {
            os.write(data, 0, len);
        }
        os.flush();
        is.close();
        os.close();
    }

}
