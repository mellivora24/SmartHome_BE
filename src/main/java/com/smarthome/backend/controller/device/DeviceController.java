package com.smarthome.backend.controller.device;

import com.smarthome.backend.dto.DataResponse;
import com.smarthome.backend.dto.Response;
import com.smarthome.backend.dto.deviceDTO.request.DeviceRequest;
import com.smarthome.backend.dto.userDTO.request.UserRequest;
import com.smarthome.backend.service.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users/{userId}/devices")
@AllArgsConstructor
public class DeviceController {
    private final CommonService commonService;

    @GetMapping
    public ResponseEntity<DataResponse<Object>> findAll(@PathVariable String userId) {
        String collectionPath = "users/" + userId + "/devices";
        List<Object> response = commonService.getAllDocuments(collectionPath);

        DataResponse<Object> dataResponse = DataResponse.<Object>builder()
                .message("Fetched all devices successfully")
                .data(response)
                .success(true)
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<Response<Object>> findById(@PathVariable String userId, @PathVariable String deviceId) {
        String collectionPath = "users/" + userId + "/devices";
        Object document = commonService.getDocumentById(collectionPath, deviceId);

        Response<Object> dataResponse = Response.<Object>builder()
                .message("Device fetched successfully")
                .data(document)
                .success(true)
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    public ResponseEntity<Response<Object>> create(@PathVariable String userId, @RequestBody DeviceRequest deviceRequest) {
        String collectionPath = "users/" + userId + "/devices";
        String id = commonService.createDocument(collectionPath, deviceRequest);

        Response<Object> dataResponse = Response.<Object>builder()
                .message("Device created successfully")
                .data(Map.of("id", id))
                .success(true)
                .build();
        return ResponseEntity.status(201).body(dataResponse);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<Response<Object>> update(@PathVariable String userId, @PathVariable String deviceId, @RequestBody DeviceRequest deviceRequest) {
        String collectionPath = "users/" + userId + "/devices";
        Map<String, Object> currentData = (Map<String, Object>) commonService.getDocumentById(collectionPath, deviceId);
        if (currentData == null) throw new RuntimeException("Device not found with id: " + deviceId);

        Map<String, Object> updatedData = new HashMap<>(currentData);
        if (deviceRequest.getDeviceData() != null) updatedData.put("deviceData", deviceRequest.getDeviceData());
        if (deviceRequest.getDeviceName() != null) updatedData.put("deviceName", deviceRequest.getDeviceName());
        if (deviceRequest.getDevicePort() != null) updatedData.put("devicePort", deviceRequest.getDevicePort());
        if (deviceRequest.getDeviceType() != null) updatedData.put("deviceType", deviceRequest.getDeviceType());

        commonService.updateDocument(collectionPath, deviceId, updatedData);

        Response<Object> dataResponse = Response.<Object>builder()
                .message("Device updated successfully")
                .data(Map.of("id", deviceId))
                .success(true)
                .build();

        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<Response<Object>> delete(@PathVariable String userId, @PathVariable String deviceId) {
        String collectionPath = "users/" + userId + "/devices";
        commonService.deleteDocument(collectionPath, deviceId);

        Response<Object> dataResponse = Response.<Object>builder()
                .message("Device deleted successfully")
                .success(true)
                .build();
        return ResponseEntity.ok(dataResponse);
    }
}

