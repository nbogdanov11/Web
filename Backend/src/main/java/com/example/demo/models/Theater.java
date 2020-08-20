package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "theaters")
@Getter
@Setter
@AllArgsConstructor
public class Theater {

    @Id
    private long id;

    private int seats;

    private String name;

    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    public Theater(){
        this.deleted = false;
    }
}
