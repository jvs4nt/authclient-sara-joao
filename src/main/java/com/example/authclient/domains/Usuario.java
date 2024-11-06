package com.example.authclient.domains;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@With
@Table(name = "Usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String email;

    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Permissao> listaPermissoes;

    @Override
    //Qual o tamanho e a quantidade de autoridades que nosso usuário tem. transformando a lista
    // em um fluxo e vou encadeando os mapeamentos para um fluxo de permissao enum e depois para
    // um fluxo de autoridades e depois coleto numa lista
    //Lista de papéis e lista de autoridades
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = this.listaPermissoes.stream()
                .map(Permissao::getEnumsPermissao)
                .map(RoleEnum::name)
                .map(SimpleGrantedAuthority::new)
                .toList();
        List<SimpleGrantedAuthority> autoridades = new ArrayList<>(this.listaPermissoes.stream()
                .map(Permissao::getEnumsPermissao)
                .map(RoleEnum::getAutoridades)
                .flatMap(List::stream)
                .map(SimpleGrantedAuthority::new)
                .toList());
        autoridades.addAll(roles);
        return autoridades;
    }

    @Override
    public String getPassword() {

        return this.senha;
    }

    @Override
    public String getUsername() {

        return this.email;
    }
}

