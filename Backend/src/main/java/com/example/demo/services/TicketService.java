package com.example.demo.services;

import com.example.demo.TicketStatus;
import com.example.demo.dto.TicketDTO;
import com.example.demo.models.Projection;
import com.example.demo.models.Ticket;
import com.example.demo.models.User;
import com.example.demo.repos.ProjectionRepo;
import com.example.demo.repos.TicketRepo;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private ProjectionRepo projectionRepo;

    @Autowired
    private UserRepo userRepo;

    public void reserveTicket(TicketDTO request) throws Exception{
        Ticket ticket = new Ticket();
        Projection projection = projectionRepo.findOneById(request.getProjectionId());
        if(projection.getFreeSeats() == 0){
            throw new Exception("Sala je popunjena.");
        }
        projection.setFreeSeats(projection.getFreeSeats() - 1);
        User viewer = userRepo.findOneById(request.getViewerId());
        long id = 1;
        if(!ticketRepo.findAll().isEmpty()){
            id = ticketRepo.findAll().size() + 1;
        }
        ticket.setId(id);
        ticket.setStatus(TicketStatus.RESERVED);
        ticket.setProjection(projection);
        ticket.setViewer(viewer);
        Ticket saved = ticketRepo.save(ticket);
        viewer.getTickets().add(saved);
        userRepo.save(viewer);
        projection.setTicket(ticket);
        projectionRepo.save(projection);
    }

    public void payTicket(Long id){
        Projection projection = projectionRepo.findOneById(id);
        Ticket ticket = projection.getTicket();
        ticket.setStatus(TicketStatus.PAID);
        ticketRepo.save(ticket);
    }

    public void cancelTicket(Long id){
        Projection projection = projectionRepo.findOneById(id);
        Ticket ticket = projection.getTicket();
        ticket.setStatus(TicketStatus.CANCELED);
        ticketRepo.save(ticket);
        projection.setFreeSeats(projection.getFreeSeats() + 1);
    }
}
