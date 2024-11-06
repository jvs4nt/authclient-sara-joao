package com.example.authclient.gateways;

import com.example.authclient.domains.Permissao;
import com.example.authclient.domains.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import javax.management.relation.Role;
import java.util.List;

@Data
@NoArgsConstructor
public class UsuarioRequest {

    private String username;
    private String password;
    private List<RoleEnum> roles;

}
