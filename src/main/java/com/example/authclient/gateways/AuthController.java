package com.example.authclient.gateways;

import com.example.authclient.domains.Permissao;
import com.example.authclient.domains.Usuario;
import com.example.authclient.usecases.GetJwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping// Deve ser acessado por todos
    public UsuarioResponse createUser(@RequestBody UsuarioRequest usuarioRequest) {

            // Criar a lista de permissões com base nos roles recebidos no JSON
            List<Permissao> permissoes = usuarioRequest.getRoles().stream()
                    .map(role -> Permissao.builder().enumsPermissao(role).build())
                    .toList();

            // Construir o novo usuário com as permissões e senha codificada
            Usuario novoUsuario = Usuario.builder()
                    .email(usuarioRequest.getUsername())
                    .senha(passwordEncoder.encode(usuarioRequest.getPassword()))
                    .listaPermissoes(permissoes)
                    .build();
            usuarioRepository.save(novoUsuario);

            // Retornar uma resposta com o nome de usuário criado
            UsuarioResponse response = new UsuarioResponse();
            response.setUsername(novoUsuario.getEmail());

            return response;
        }

    @GetMapping//Deve ser acessado via autenticacao basica por usuario com ROLE_USER
    public String getJwt(Authentication authentication) {
        return "Hello, " + authentication.getName();
    }
}


