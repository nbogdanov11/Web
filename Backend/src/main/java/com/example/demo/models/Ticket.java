package com.example.demo.models;

import com.example.demo.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "tickets")
@Getter
@Setter
@AllArgsConstructor
public class Ticket {

    @Id
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projection_id", referencedColumnName = "id")
    private Projection projection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viewer_id")
    private User viewer;

    private TicketStatus status;
}
