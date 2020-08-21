package com.example.demo.models;

import com.example.demo.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private long id;

    private String username; //email

    private String password;

    private String name;

    private String surname;

    private String phone;

    private LocalDate birthday;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    private boolean activated;

    @ManyToMany(mappedBy = "managers")
    private List<Cinema> cinemas = new ArrayList<>();

    @OneToMany(mappedBy = "viewer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "viewer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rate> rates = new ArrayList<>();
}
