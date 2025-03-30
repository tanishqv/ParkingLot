package com.scaler.repositories;

import com.scaler.models.Ticket;

import java.util.HashMap;
import java.util.Map;

public class TicketRepository {
    private Map<Long, Ticket> ticketMap = new HashMap<>();
    private Long previousTicketId = 0L;

    public Ticket save(Ticket ticket) {
        if (ticket.getId() == null) {
            previousTicketId++;
            ticket.setId(previousTicketId);
            ticketMap.put(previousTicketId, ticket);
        }
        return ticket;
    }
}
