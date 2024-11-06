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
@Table(name = "hte_usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq", allocationSize = 1)
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
        return this.listaPermissoes.stream()
                .flatMap(permissao -> {
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(permissao.getEnumsPermissao().name()));
                    authorities.addAll(permissao.getEnumsPermissao().getAutoridades().stream()
                            .map(SimpleGrantedAuthority::new)
                            .toList());
                    return authorities.stream();
                })
                .toList();
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

