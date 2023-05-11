package org.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.example.common.response.ResponseData;
import org.example.common.util.UUIDUtil;
import org.example.entity.Accuse;
import org.example.entity.AccusePicture;
import org.example.entity.query.AccuseParam;
import org.example.mapper.AccuseMapper;
import org.example.mapper.AccusePictureMapper;
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
 * 投诉记录表 前端控制器
 * </p>
 *
 * @author 黄嘉诚
 * @since 2023-05-06
 */
@RestController
@RequestMapping("/accuse")
public class AccuseController {

    @Resource
    AccuseMapper accuseMapper;

    @Resource
    AccusePictureMapper accusePictureMapper;

    @Value("${accuse.picture.save.path}")
    private String accusePictureSavePath;

    @PostMapping("/save")
    public ResponseData save(Accuse accuse, MultipartFile file) throws IOException {
        accuseMapper.insert(accuse);
        if (file != null) {
            File folder = new File(accusePictureSavePath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String fileName = UUIDUtil.uniqueFileName(file.getOriginalFilename());
            File saveFile = new File(accusePictureSavePath, fileName);
            file.transferTo(saveFile);
            AccusePicture picture = new AccusePicture();
            picture.setAccuseId(accuse.getId());
            picture.setFileName(fileName);
            accusePictureMapper.insert(picture);
        }
        return ResponseData.success();
    }

    @PostMapping("/apply")
    public ResponseData apply(Accuse accuse) {
        accuseMapper.updateById(accuse);
        return ResponseData.success();
    }

    @GetMapping("/picture/download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        // 根据文件名下载文件
        OutputStream os = response.getOutputStream();
        File file = new File(accusePictureSavePath, fileName);
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

    @GetMapping("/query")
    public ResponseData query (AccuseParam param) {
        QueryWrapper qw = new QueryWrapper();
        qw.select("id", "type_name", "title", "state");
        qw.like(StringUtils.isNotEmpty(param.getTitle()), "title", param.getTitle());
        qw.eq(param.getTypeId()!= null, "type_id", param.getTypeId());
        qw.eq(param.getUserId()!= null, "user_id", param.getUserId());
        qw.eq(param.getWorkerId()!= null, "worker_id", param.getWorkerId());
        qw.eq(param.getState()!= null, "state", param.getState());
        qw.ge(param.getHighDate()!=null && param.getHighDate()==null, "create_date", param.getHighDate());
        qw.le(param.getLowDate()==null && param.getLowDate()!=null, "create_date", param.getLowDate());
        qw.between(param.getLowDate()!=null && param.getHighDate()!= null, "create_date", param.getLowDate(),param.getHighDate());
        qw.orderByDesc("create_date");
        accuseMapper.selectPage(param, qw);
        return ResponseData.success(param);
    }

    @GetMapping("/del/{id}")
    public ResponseData del(@PathVariable("id") Integer id) {
        accuseMapper.deleteById(id);
        return ResponseData.success();
    }

    @GetMapping("/detail/{id}")
    public ResponseData detail(@PathVariable("id") Integer id) {
        Accuse accuse = accuseMapper.selectById(id);
        QueryWrapper qw = new QueryWrapper();
        qw.eq("accuse_id", id);
        List list = accusePictureMapper.selectList(qw);
        Map<String, Object> map = new HashMap<>();
        map.put("accuse", accuse);
        map.put("pictureList", list);
        return ResponseData.success(map);
    }

}
