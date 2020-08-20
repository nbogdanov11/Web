package com.example.demo.models;

import com.example.demo.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

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
}
