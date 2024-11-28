package com.smarthome.backend.dto.deviceDTO.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceRequest {
    private String uid;
    private String deviceData;
    private String deviceName;
    private String devicePort;
    private String deviceType;
    private String userUid;
}