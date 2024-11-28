package com.smarthome.backend.entity;

import com.smarthome.backend.entity.Device;

import java.util.List;

public class User {
    private String uid;
    private String email;
    private String name;
    private String phone;
    private List<Device> devices;

    public User() {}

    public User(String uid, String email, String name, String phone, List<Device> devices) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.devices = devices;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", devices=" + devices +
                '}';
    }
}
