package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cinemas")
@Getter
@Setter
@AllArgsConstructor
public class Cinema {

    @Id
    private long id;

    private String name;

    private String address;

    private String email;

    private String phone;

    private boolean deleted;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "cinema_manager",
            joinColumns = @JoinColumn(name = "cinema_id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id")
    )
    List<User> managers = new ArrayList<>();

    public Cinema(){
        this.deleted = false;
    }
}
