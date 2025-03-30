package com.scaler.strategies;

import com.scaler.models.ParkingSpot;
import com.scaler.models.VehicleType;

public interface SpotAllocationStrategy {
    ParkingSpot allocateSpot(VehicleType vehicleType);
}
