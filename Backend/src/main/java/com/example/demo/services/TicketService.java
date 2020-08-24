package com.example.demo.services;

import com.example.demo.TicketStatus;
import com.example.demo.dto.TicketDTO;
import com.example.demo.models.Projection;
import com.example.demo.models.Ticket;
import com.example.demo.models.User;
import com.example.demo.repos.ProjectionRepo;
import com.example.demo.repos.RateRepo;
import com.example.demo.repos.TicketRepo;
import com.example.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;

@Service
public class TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    private ProjectionRepo projectionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RateRepo rateRepo;

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
        projection.getTickets().add(ticket);
        projectionRepo.save(projection);
    }

    public void payTicket(Long id){
        Ticket ticket = ticketRepo.findOneById(id);
        ticket.setStatus(TicketStatus.PAID);
        ticketRepo.save(ticket);
    }

    public void cancelTicket(Long id){
        Ticket ticket = ticketRepo.findOneById(id);
        ticket.setStatus(TicketStatus.CANCELED);
        ticketRepo.save(ticket);
        Projection projection = ticket.getProjection();
        projection.setFreeSeats(projection.getFreeSeats() + 1);
        projectionRepo.save(projection);
    }

    public List<TicketDTO> unratedFilmsByViewer(Long id){
        List<Ticket> allTickets = ticketRepo.findAll();
        List<TicketDTO> responses = new ArrayList<>();
        LocalDateTime now = now();
        for(Ticket t: allTickets){
            if(t.getViewer().getId() == id && t.getStatus().equals(TicketStatus.PAID) && t.getProjection().getTime().isBefore(now) && rateRepo.findOneByViewer_IdAndFilm_Id(id, t.getProjection().getFilm().getId()) == null){
                TicketDTO response = new TicketDTO();
                response.setFilmName(t.getProjection().getFilm().getName());
                response.setFilmGenre(t.getProjection().getFilm().getGenre());
                response.setFilmDuration(String.valueOf(t.getProjection().getFilm().getDuration()));
                response.setFilmId(t.getProjection().getFilm().getId());
                responses.add(response);
            }
        }
        return responses;
    }

    public List<TicketDTO> getAllReservedTicketsByViewer(Long id){
        User user = userRepo.findOneById(id);
        List<Ticket> allTickets = ticketRepo.findAll();
        LocalDateTime now = now();
        List<TicketDTO> responses = new ArrayList<>();
        for(Ticket t: allTickets){
            if(t.getStatus().equals(TicketStatus.RESERVED) && t.getProjection().getTime().isAfter(now) && t.getViewer() == user){
                TicketDTO response = new TicketDTO();
                response.setId(t.getId());
                response.setFilmName(t.getProjection().getFilm().getName());
                response.setFilmDuration(String.valueOf(t.getProjection().getFilm().getDuration()));
                response.setFilmGenre(t.getProjection().getFilm().getGenre());
                response.setCinemaName(t.getProjection().getTheater().getCinema().getName());
                response.setTime(t.getProjection().getTime());
                responses.add(response);
            }
        }
        return responses;
    }
}
