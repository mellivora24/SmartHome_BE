package com.smarthome.backend.service;

import com.smarthome.backend.dto.deviceDTO.request.DeviceRequest;
import com.smarthome.backend.dto.userDTO.request.UserRequest;

import java.util.List;
import java.util.Map;

public interface CommonService {
    List<Object> getAllDocuments(String collectionName);
    Object getDocumentById(String collectionName, String documentId);
    String createDocument(String collectionName, UserRequest userRequest);
    String createDocument(String collectionName, DeviceRequest deviceRequest);
    void updateDocument(String collectionName, String documentId, Map<String, Object> data);
    void deleteDocument(String collectionName, String documentId);

    Object getDocumentByEmail(String collection, String email);
}
