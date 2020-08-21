package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectionDTO {

    private long id;

    private LocalDateTime time;

    private int price;

    private long filmId;

    private long theaterId;

    private String filmName;

    private String filmGenre;

    private int filmDuration;

    private String theaterName;

    private String cinemaName;

    private int freeSeats;
}
