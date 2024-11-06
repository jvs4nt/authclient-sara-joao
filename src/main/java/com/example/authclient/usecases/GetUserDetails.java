package com.example.authclient.usecases;

import com.example.authclient.gateways.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Essa classe permite que utilizemos os dados do banco de dados com a segurança fornecida pelo Spring Security
@Service
@RequiredArgsConstructor //injecao pro construtor
public class GetUserDetails implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
    }
}
