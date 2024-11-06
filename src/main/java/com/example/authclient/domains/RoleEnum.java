package com.example.authclient.domains;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum RoleEnum {
    ROLE_USER(new ArrayList<>()),
    ROLE_ALUNO(List.of("ALUNO")),
    ROLE_PROFESSOR(List.of("PROFESSOR")),
    ROLE_AUTH(List.of("AUTH")),
    UPDATE(List.of("DADOS"));

    private List<String> autoridades;

    RoleEnum(List<String> autoridades) {
        this.autoridades = autoridades;
    }
}
