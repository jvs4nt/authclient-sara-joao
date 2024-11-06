package com.example.authclient.domains;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Integer id;

    private String ra;

    private String nome;
}