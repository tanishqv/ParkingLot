package com.scaler;

import com.scaler.controllers.TicketController;
import com.scaler.dtos.IssueTicketRequestDTO;
import com.scaler.dtos.IssueTicketResponseDTO;
import com.scaler.exceptions.GateNotFoundException;
import com.scaler.exceptions.NoEmptySpotsException;
import com.scaler.models.VehicleType;
import com.scaler.repositories.GateRepository;
import com.scaler.repositories.ParkingSpotRepository;
import com.scaler.repositories.TicketRepository;
import com.scaler.repositories.VehicleRepository;
import com.scaler.services.TicketService;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws GateNotFoundException, NoEmptySpotsException {
        IssueTicketRequestDTO requestDTO = new IssueTicketRequestDTO();
        requestDTO.setVehicleOwnerName("Some name");
        requestDTO.setEntryTime(new Date());
        requestDTO.setVehicleNumber("STARK007");
        requestDTO.setEntryGateId(1L);
        requestDTO.setVehicleType(VehicleType.SUV);


        GateRepository gateRepository = new GateRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();
        TicketRepository ticketRepository = new TicketRepository();

        TicketService ticketService = new TicketService(gateRepository, vehicleRepository, parkingSpotRepository, ticketRepository);
        TicketController ticketController = new TicketController(ticketService);

        IssueTicketResponseDTO ticketResponseDTO = ticketController.issueTicket(requestDTO);
        System.out.println("Response Status: " + ticketResponseDTO.getResponseStatus());
        System.out.println("Ticket ID: " + ticketResponseDTO.getTicket().getId());
    }
}