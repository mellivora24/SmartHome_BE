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
@RequestMapping("/api/devices")
@AllArgsConstructor
public class DeviceController {
    private final CommonService commonService;
    private final String COLLECTION = "devices";

    @GetMapping
    public ResponseEntity<DataResponse<Object>> findAll() {
        List<Object> response = commonService.getAllDocuments(COLLECTION);
        DataResponse<Object> dataResponse = DataResponse.<Object>builder()
                .message("Fetched all documents successfully")
                .data(response)
                .success(true)
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Object>> findById(@PathVariable String id) {
        Object document = commonService.getDocumentById(COLLECTION, id);
        Response<Object> dataResponse = Response.<Object>builder()
                .message("Document fetched successfully")
                .data(document)
                .success(true)
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    public ResponseEntity<Response<Object>> create(@RequestBody DeviceRequest deviceRequest) {
        String id = commonService.createDocument(COLLECTION, deviceRequest);
        Response<Object> dataResponse = Response.<Object>builder()
                .message("Document created successfully")
                .data(Map.of("id", id))
                .success(true)
                .build();
        return ResponseEntity.status(201).body(dataResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Object>> update(@PathVariable String id, @RequestBody DeviceRequest deviceRequest) {
        Map<String, Object> data = new HashMap<>();
        data.put("uid", id);
        data.put("deviceData", deviceRequest.getDeviceData());
        data.put("deviceName", deviceRequest.getDeviceName());
        data.put("devicePort", deviceRequest.getDevicePort());
        data.put("deviceType", deviceRequest.getDeviceType());

        commonService.updateDocument(COLLECTION, id, data);
        Response<Object> dataResponse = Response.<Object>builder()
                .message("Document updated successfully")
                .data(Map.of("id", id))
                .success(true)
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Object>> delete(@PathVariable String id) {
        commonService.deleteDocument(COLLECTION, id);
        Response<Object> dataResponse = Response.<Object>builder()
                .message("Document deleted successfully")
                .success(true)
                .build();
        return ResponseEntity.ok(dataResponse);
    }
}
