package org.example.common.util;

import java.util.UUID;

public class UUIDUtil {
    public static String uniqueFileName (String fileName) {
                // 时间戳
        return  System.currentTimeMillis() +
                // 拼接32位UUID (转大写, 去除-)
                UUID.randomUUID().toString().toUpperCase().replaceAll("-","") +
                // 拼接源文件的后缀名
                fileName.substring(fileName.lastIndexOf("."));
    }
}
