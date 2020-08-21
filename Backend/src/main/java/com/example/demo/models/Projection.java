package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "projections")
@Getter
@Setter
@AllArgsConstructor
public class Projection {

    @Id
    private long id;

    private LocalDateTime time;

    private int freeSeats;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @OneToOne(mappedBy = "projection", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Ticket ticket;

    private int price;

    private boolean deleted;

    public Projection(){
        this.deleted = false;
    }
}
