package com.scaler.dtos;

import com.scaler.models.VehicleType;

import java.util.Date;

public class IssueTicketRequestDTO {
    private long entryGateId;
    private VehicleType vehicleType;
    private String vehicleNumber;
    private Date entryTime;
    private String vehicleOwnerName;

    public long getEntryGateId() {
        return entryGateId;
    }

    public void setEntryGateId(long entryGateId) {
        this.entryGateId = entryGateId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getVehicleOwnerName() {
        return vehicleOwnerName;
    }

    public void setVehicleOwnerName(String vehicleOwnerName) {
        this.vehicleOwnerName = vehicleOwnerName;
    }
}
