package org.example.entity.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.example.entity.Task;


@Data
public class TaskParam extends Page<Task> {
    private String title;
    private Integer typeId;
    private Integer volunteerId;
    private Integer createBy;
    private Integer state;
    private String highDate;
    private String lowDate;
}
