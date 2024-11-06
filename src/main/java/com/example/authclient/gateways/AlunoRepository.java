package com.example.authclient.gateways;

import com.example.authclient.domains.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, String> {

Optional<Aluno> findById(String ra);
}
