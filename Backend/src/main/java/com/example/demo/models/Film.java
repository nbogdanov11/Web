package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "films")
@Getter
@Setter
@AllArgsConstructor
public class Film {

    @Id
    private long id;

    private String name;

    private String description;

    private int duration;

    private String genre;

    private boolean deleted;

    public Film(){
        this.deleted = false;
    }
}
