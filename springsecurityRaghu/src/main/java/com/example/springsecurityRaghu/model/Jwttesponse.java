package com.example.springsecurityRaghu.model;

import lombok.*;

@Data
@AllArgsConstructor
@Setter
@NoArgsConstructor

@Getter
public class Jwttesponse {
    private String token;
    private String description;
}
