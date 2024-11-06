package com.example.authclient.gateways;

import com.example.authclient.domains.Aluno;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoRepository alunoRepository;

    @Operation(summary = "Obter informações do aluno", description = "Retorna as informações do aluno pelo ID, acessível apenas por usuários com a ROLE_ALUNO.")
    @PreAuthorize("hasRole('ALUNO')")
    @GetMapping("/{alunoId}") // Acessado por autenticação básica por usuários que possuam ROLE_ALUNO
    public AlunoResponse getAluno(Authentication authentication, @PathVariable String alunoId) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(alunoId);
        return AlunoResponse.builder().nome("Retornando aluno de ID " + alunoId).build();
    }

    @Operation(summary = "Criar um novo aluno", description = "Permite a criação de um novo aluno, acessível apenas por usuários com a ROLE_PROFESSOR.")
    @PreAuthorize("hasRole('PROFESSOR')")
    @PostMapping // Acessado por autenticação básica por usuários que possuam ROLE_PROFESSOR
    public AlunoResponse createAluno(Authentication authentication, @RequestBody AlunoRequest alunoRequest) {
        return AlunoResponse.builder().nome(alunoRequest.getNome()).build();
    }

    @Operation(summary = "Atualizar informações do aluno", description = "Atualiza as informações do aluno, acessível apenas por usuários com autoridade de UPDATE.")
    @PreAuthorize("hasAuthority('UPDATE')")
    @PutMapping // Acessado via JWT com autoridade de UPDATE
    public AlunoResponse updateAluno(@RequestBody AlunoRequest alunoRequest) {
        return AlunoResponse.builder().nome(alunoRequest.getNome()).build();
    }
}
