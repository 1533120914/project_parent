package org.example.entity.view;

import lombok.Data;

@Data
public class ProviderDeviceView {
    private Integer providerId;
    private String providerName;
    private Integer deviceId;
    private String deviceName;
    private Double price;
}
