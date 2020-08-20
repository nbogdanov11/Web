package com.example.demo.dto;

import com.example.demo.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long id;

    private String username;

    private String password;

    private String name;

    private String surname;

    private String phone;

    private LocalDate birthday;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;
}
