package org.example.entity.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.example.entity.Volunteer;

@Data
public class VolunteerParam extends Page<Volunteer> {
    private String username;
    private String mobile;
    private String highDate;
    private String lowDate;
}
