package com.scaler.repositories;

import com.scaler.models.ParkingSpot;

import java.util.HashMap;
import java.util.Map;

public class ParkingSpotRepository {
    private Map<Long, ParkingSpot> spotsMap = new HashMap<>();
    private Long previousSpotId = 0L;

    public ParkingSpot save(ParkingSpot parkingSpot) {
        if (parkingSpot.getId() != null) {
            spotsMap.put(parkingSpot.getId(), parkingSpot);
        } else {
            previousSpotId++;
            parkingSpot.setId(previousSpotId);
            spotsMap.put(previousSpotId, parkingSpot);
        }
        return parkingSpot;
    }
}
