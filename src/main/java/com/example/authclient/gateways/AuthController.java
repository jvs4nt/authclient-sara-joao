package com.example.authclient.gateways;

import com.example.authclient.domains.Permissao;
import com.example.authclient.domains.Usuario;
import com.example.authclient.usecases.GetJwtToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final GetJwtToken getJwtToken;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Register a new user", description = "Registra um novo usuário com os dados recebidos")
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> createUser(@RequestBody UsuarioRequest usuarioRequest) {
        if (usuarioRequest == null || usuarioRequest.getUsername() == null || usuarioRequest.getPassword() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        List<Permissao> permissoes = usuarioRequest.getRoles().stream()
                .map(role -> Permissao.builder().enumsPermissao(role).build())
                .toList();

        Usuario novoUsuario = Usuario.builder()
                .email(usuarioRequest.getUsername())
                .senha(passwordEncoder.encode(usuarioRequest.getPassword()))
                .listaPermissoes(permissoes)
                .build();
        usuarioRepository.save(novoUsuario);

        UsuarioResponse response = new UsuarioResponse();
        response.setUsername(novoUsuario.getEmail());

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get JWT token", description = "Token JWT para usuários autenticados")
    @GetMapping("/getJwt")
    public String getJwt(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }
}
