package com.scaler.repositories;

import com.scaler.models.Gate;
import com.scaler.models.Vehicle;
import com.scaler.models.VehicleType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {
    private Map<Long, Vehicle> vehicleMap = new HashMap<>();
    private Long previousVehicleId = 0L;

    public Vehicle save(Vehicle vehicle) {
        if (vehicle.getId() == null) {
            previousVehicleId++;
            vehicle.setId(previousVehicleId);
            vehicleMap.put(previousVehicleId, vehicle);
        }
        return vehicle;
    }

    public Optional<Vehicle> findVehicleById(Long id) {
        if (vehicleMap.containsKey(id)) {
            return Optional.of(vehicleMap.get(id));
        }
        return Optional.empty();
    }

    public Vehicle findVehicleByVehicleNumber(String vehicleNumber) {
        for (Map.Entry<Long, Vehicle> entry: vehicleMap.entrySet()) {
            if (entry.getValue().getVehicleNumber().equals(vehicleNumber)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
