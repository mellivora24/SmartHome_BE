package com.smarthome.backend.entity;

public class Device {
    private String deviceID;
    private String devicePort;
    private String deviceName;
    private String deviceType;
    private String deviceData;

    public Device() {}

    public Device(String deviceID, String devicePort, String deviceName, String deviceType, String deviceData) {
        this.deviceID = deviceID;
        this.devicePort = devicePort;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.deviceData = deviceData;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDevicePort() {
        return devicePort;
    }

    public void setDevicePort(String devicePort) {
        this.devicePort = devicePort;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(String deviceData) {
        this.deviceData = deviceData;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceID='" + deviceID + '\'' +
                ", devicePort='" + devicePort + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceData='" + deviceData + '\'' +
                '}';
    }
}
