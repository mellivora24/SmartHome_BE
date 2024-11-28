package com.smarthome.backend.controller.user;

import com.smarthome.backend.dto.DataResponse;
import com.smarthome.backend.dto.Response;
import com.smarthome.backend.dto.userDTO.request.UserRequest;
import com.smarthome.backend.service.CommonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final CommonService commonService;
    private final String COLLECTION = "users";

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

    @GetMapping("/email/{email}")
    public ResponseEntity<Response<Object>> findByEmail(@PathVariable String email) {
        Object document = commonService.getDocumentByEmail(COLLECTION, email);
        Response<Object> dataResponse = Response.<Object>builder()
                .message("Document fetched successfully")
                .data(document)
                .success(true)
                .build();
        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    public ResponseEntity<Response<Object>> create(@RequestBody UserRequest userRequest) {
        String id = commonService.createDocument(COLLECTION, userRequest);
        Response<Object> dataResponse = Response.<Object>builder()
                .message("Document created successfully")
                .data(Map.of("id", id))
                .success(true)
                .build();
        return ResponseEntity.status(201).body(dataResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Object>> update(@PathVariable String id, @RequestBody UserRequest userRequest) {
        Map<String, Object> currentData = (Map<String, Object>) commonService.getDocumentById(COLLECTION, id);
        if (currentData == null) {
            throw new RuntimeException("User not found with id: " + id);
        }

        Map<String, Object> updatedData = new HashMap<>(currentData);
        if (userRequest.getPhone() != null) updatedData.put("phone", userRequest.getPhone());
        if (userRequest.getName() != null) updatedData.put("name", userRequest.getName());
        if (userRequest.getEmail() != null) updatedData.put("email", userRequest.getEmail());

        commonService.updateDocument(COLLECTION, id, updatedData);

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
