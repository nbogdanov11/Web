package com.example.demo.dto;

import lombok.Data;

@Data
public class FilmDTO {

    private String name;

    private String description;

    private String genre;

    private int duration;

    private long id;
}
