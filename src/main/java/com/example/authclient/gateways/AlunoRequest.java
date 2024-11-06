package com.example.authclient.gateways;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AlunoRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String ra;

}
