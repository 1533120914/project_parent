package org.example.entity.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.example.entity.view.DeviceProcurementView;

@Data
public class DeviceProcurementViewParam extends Page<DeviceProcurementView> {
    private Integer state;
    private Integer providerId;
    private Integer deviceTypeId;
    private Integer buyerId;
}
