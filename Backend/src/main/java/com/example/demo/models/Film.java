package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Projection> projections = new ArrayList<>();

    public Film(){
        this.deleted = false;
    }
}
