package com.example.secpart3.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder

public class User {
    @Id
    int id;
    private String username;
    private String password;
}
