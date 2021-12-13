package com.example.springsecurityRaghu.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private String email;
    @ElementCollection
    private Set<String> role;
    private String status;
}
