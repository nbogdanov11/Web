package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterDTO {

    private long id;

    private String name;

    private int seats;

    private long cinemaId;

    private String cinemaName;
}
