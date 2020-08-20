package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Theater> theaters = new HashSet<>();

    public Cinema(){
        this.deleted = false;
    }
}
