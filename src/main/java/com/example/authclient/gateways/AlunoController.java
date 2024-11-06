package com.example.authclient.gateways;

import com.example.authclient.domains.Aluno;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoRepository alunoRepository;

    @GetMapping("/{alunoId}") // Acessado por autenticacao basica por usuarios que possuam ROLE_ALUNO
    public AlunoResponse getAluno(Authentication authentication, @PathVariable String alunoId) {
        Optional<Aluno> optinalAluno = alunoRepository.findById(alunoId);
        return AlunoResponse.builder().nome("retornando aluno de id " + alunoId).build();
    }

    @PostMapping// Acessado por autenticacao basica por usuarios que possuam ROLE_PROFESSOR
    public AlunoResponse createAluno(Authentication authentication, @RequestBody AlunoRequest alunoRequest) {
        return AlunoResponse.builder().nome(alunoRequest.getNome()).build();
    }

    @PutMapping//Acessado via JWT com autoridade de UPDATE
    public AlunoResponse updateAluno(@RequestBody AlunoRequest alunoRequest) {
        return AlunoResponse.builder().nome(alunoRequest.getNome()).build();
    }



}
