package org.example;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;

import java.util.Collections;

// 这个单元测试类不需要启动SpringBoot, 不用加@SpringBootTest注解
public class MyBatisCodeGenerator {
    // 代码生成器连接数据库的配置信息
    private static String url = "jdbc:mysql://localhost:3306/db_volunteer?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "root";
    private static String author = "许婉"; // 作者名
    // 生成的主java代码输出目录 (右击src/main/java 选择复制绝对路径)
    private static String javaOutputDir = "E:\\IdeaProjects\\project_parent\\volunteer\\back-end\\src\\main\\java";
    // 生成的mapper.xml文件的输出目录
    private static String mapperOutputDir = "E:\\IdeaProjects\\project_parent\\volunteer\\back-end\\src\\main\\resources\\mapper";
    // 设置父包名
    private static String parentPackage = "org.example";


    @Test
    public void generator() {
        // 根据url, username, password连接数据库
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    // 设置类的作者名
                    builder.author(author)
                            // Java代码文件(controller,service,mapper,entity)的输出目录
                            .outputDir(javaOutputDir);
                })
                .packageConfig(builder -> {
                    // Java主代码的父包名
                    builder.parent(parentPackage)
                            // mapper.xml文件的输出目录
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperOutputDir));
                })
                .strategyConfig(builder -> {
                    // 是否允许使用Lombok
                    builder.entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("is_delete")
                            .addTableFills(new Column("create_date", FieldFill.INSERT),new Column("modify_date", FieldFill.UPDATE));
                    // 是否使用驼峰转换命名, 开启Rest风格的控制器
                    builder.controllerBuilder()
                            .enableHyphenStyle().enableRestStyle();
                    // 生成哪张表
                    // builder.addInclude("user"); 一张表
                    // builder.addInclude("user","car","rent_record"); 多张表
                    // builder.addExclude(""); 所有的表 (没有排除任何一个)
                    // builder.addExclude("user"); 排除这一张表
                    // builder.addExclude("user", "car", "rent_record"); 排除多张表
                    builder.addExclude("");
                }).execute();
    }
}
