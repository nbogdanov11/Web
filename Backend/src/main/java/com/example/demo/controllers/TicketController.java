package com.example.demo.controllers;

import com.example.demo.dto.TicketDTO;
import com.example.demo.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public void reserveTicket(@RequestBody TicketDTO request) throws Exception{
        ticketService.reserveTicket(request);
    }

    @PutMapping("/pay/{id}/ticket")
    public void payTicket(@PathVariable Long id, @RequestBody TicketDTO emptyBody){
        ticketService.payTicket(id);
    }

    @PutMapping("/cancel/{id}/ticket")
    public void cancelTicket(@PathVariable Long id, @RequestBody TicketDTO emptyBody){
        ticketService.cancelTicket(id);
    }

    @GetMapping("/reserved/{id}/viewer")
    public List<TicketDTO> getAllReservedTicketsByViewer(@PathVariable Long id){
        return ticketService.getAllReservedTicketsByViewer(id);
    }

    @GetMapping("/unrated/{id}/viewer")
    public List<TicketDTO> unratedFilmsByViewer(@PathVariable Long id){
        return ticketService.unratedFilmsByViewer(id);
    }
}
