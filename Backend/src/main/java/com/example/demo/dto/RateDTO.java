package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {

    private float rate;

    private long filmId;

    private long viewerId;

    private long id;

    private float rating; //avg

    private String filmName;

    private String filmGenre;

    private String filmDescription;

    private int filmDuration;
}
