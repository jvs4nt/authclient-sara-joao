package com.example.authclient.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Permissao")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //lista de permissoes do usuario
    @Enumerated(EnumType.STRING)
    private RoleEnum enumsPermissao;


}
