package com.scaler.services;

import com.scaler.exceptions.ClosedGateException;
import com.scaler.exceptions.GateNotFoundException;
import com.scaler.exceptions.NoEmptySpotsException;
import com.scaler.factories.SpotAllocationStrategyFactory;
import com.scaler.models.*;
import com.scaler.repositories.GateRepository;
import com.scaler.repositories.ParkingSpotRepository;
import com.scaler.repositories.TicketRepository;
import com.scaler.repositories.VehicleRepository;
import com.scaler.strategies.SpotAllocationStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {

    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingSpotRepository parkingSpotRepository;
    private TicketRepository ticketRepository;


    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, ParkingSpotRepository parkingSpotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(long entryGateId, String vehicleNumber, String vehicleOwnerName, VehicleType vehicleType, SpotAllocationStrategyType spotAllocationStrategyType) throws GateNotFoundException, NoEmptySpotsException {
        Optional<Gate> gate = gateRepository.findGateById(entryGateId);
        if (gate.isEmpty()) {
            throw new GateNotFoundException("Gate with id " + entryGateId + " not found");
        }
        if (gate.get().getGateStatus().equals(GateStatus.CLOSED)) {
            throw new ClosedGateException("Cannot issue ticket for closed gate with id " + entryGateId);
        }

        Ticket ticket = new Ticket();
        ticket.setEntryGate(gate.get());
        ticket.setEntryTime(new Date());

        Vehicle savedVehicle = vehicleRepository.findVehicleByVehicleNumber(vehicleNumber);
        if (savedVehicle == null) {
            // Create new vehicle entry
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setVehicleOwner(vehicleOwnerName);

            vehicleRepository.save(vehicle);
            ticket.setVehicle(vehicle);
        }
        ticket.setVehicle(savedVehicle);

        SpotAllocationStrategy spotAllocationStrategy = SpotAllocationStrategyFactory.getStrategy(spotAllocationStrategyType);
        ParkingSpot parkingSpot = spotAllocationStrategy.allocateSpot(vehicleType);
        if (parkingSpot == null) {
            throw new NoEmptySpotsException("No empty parking spots to assign");
        }
        parkingSpot.setParkingSpotStatus(ParkingSpotStatus.OCCUPIED);
        parkingSpotRepository.save(parkingSpot);
        ticket.setParkingSpot(parkingSpot);

        return ticketRepository.save(ticket);
    }
}

