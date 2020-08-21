package com.example.demo.repos;

import com.example.demo.TicketStatus;
import com.example.demo.models.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectionRepo extends JpaRepository<Projection, Long> {

    Projection findOneById(Long id);

    List<Projection> findAllByDeletedAndTicket_Viewer_IdAndTicket_Status(boolean deleted, long id, TicketStatus status);
}
