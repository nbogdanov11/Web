package com.example.demo.dto;

import com.example.demo.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    private long id;

    private long projectionId;

    private long viewerId;

    private String filmName;

    private String filmDuration;

    private String cinemaName;

    private String theaterName;

    private LocalDateTime time;

    private int price;

    @Enumerated(value = EnumType.STRING)
    private TicketStatus status;
}
