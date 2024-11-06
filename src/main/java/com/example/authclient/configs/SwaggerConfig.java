package com.example.authclient.configs;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Configuração personalizada para o Swagger usando OpenAPI
        return new OpenAPI()
                .info(new Info()
                        .title("Auth API") // Título
                        .version("1.0") // Versão
                        .description("API com autenticação de cadastro para obter informações de alunos.") // Descrição
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))); // Licença da API e o link para mais informações
    }
}

