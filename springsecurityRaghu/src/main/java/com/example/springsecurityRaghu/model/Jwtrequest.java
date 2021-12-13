package com.example.springsecurityRaghu.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Jwtrequest {
    private String username;
    private String password;
}
