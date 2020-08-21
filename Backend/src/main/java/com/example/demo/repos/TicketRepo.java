package com.example.demo.repos;

import com.example.demo.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {

    Ticket findOneById(Long id);
}
