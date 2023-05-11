package org.example.entity.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.example.entity.Accuse;


@Data
public class AccuseParam extends Page<Accuse> {
    private String title;
    private Integer typeId;
    private Integer userId;
    private Integer workerId;
    private Integer state;
    private String highDate;
    private String lowDate;
}
