package com.smarthome.backend.service.Impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.smarthome.backend.dto.deviceDTO.request.DeviceRequest;
import com.smarthome.backend.dto.userDTO.request.UserRequest;
import com.smarthome.backend.service.CommonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Service
public class CommonServiceImpl implements CommonService {

    public List<Object> getAllDocuments(String collectionName) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collection = db.collection(collectionName);
        ApiFuture<QuerySnapshot> querySnapshot = collection.get();

        List<Object> documents = new ArrayList<>();
        try {
            for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
                documents.add(document.getData());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return documents;
    }

    public Object getDocumentById(String collectionName, String documentId) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                return document.getData();
            } else {
                throw new RuntimeException("Document not found with ID: " + documentId);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching document: " + e.getMessage());
        }
    }

    public String createDocument(String collectionName, UserRequest userRequest) {
        Firestore db = FirestoreClient.getFirestore();
        String documentId = UUID.randomUUID().toString();
        userRequest.setUid(documentId);
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        ApiFuture<WriteResult> future = docRef.set(userRequest);
        try {
            future.get();
            return docRef.getId();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating document: " + e.getMessage());
        }
    }
    public String createDocument(String collectionName, DeviceRequest deviceRequest) {
        Firestore db = FirestoreClient.getFirestore();
        String documentId = UUID.randomUUID().toString();
        deviceRequest.setUid(documentId);
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        ApiFuture<WriteResult> future = docRef.set(deviceRequest);
        try {
            future.get();
            return docRef.getId();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating document: " + e.getMessage());
        }
    }

    public void updateDocument(String collectionName, String documentId, Map<String, Object> data) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        ApiFuture<WriteResult> future = docRef.update(data);
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating document: " + e.getMessage());
        }
    }

    public void deleteDocument(String collectionName, String documentId) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        ApiFuture<WriteResult> future = docRef.delete();
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting document: " + e.getMessage());
        }
    }
}
