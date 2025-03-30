package com.scaler.controllers;

import com.scaler.dtos.IssueTicketRequestDTO;
import com.scaler.dtos.IssueTicketResponseDTO;
import com.scaler.dtos.ResponseStatus;
import com.scaler.exceptions.GateNotFoundException;
import com.scaler.models.Ticket;
import com.scaler.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO issueTicketRequestDTO) throws GateNotFoundException, NoEmptySpotsException {
        Ticket ticket = ticketService.issueTicket(
                issueTicketRequestDTO.getEntryGateId(),
                issueTicketRequestDTO.getVehicleNumber(),
                issueTicketRequestDTO.getVehicleOwnerName(),
                issueTicketRequestDTO.getVehicleType()
        );
        IssueTicketResponseDTO response = new IssueTicketResponseDTO();
        response.setTicket(ticket);
        response.setResponseStatus(ResponseStatus.SUCCESS);
        return response;
    }
}
