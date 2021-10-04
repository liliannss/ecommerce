package br.com.devs.javagirl.user.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.webmvc.ui.SwaggerConfig;

@OpenAPIDefinition(
        info = @Info(
                title = "Cadastro de Usuários",
                description = "API para cadastro de Usuário",
                contact = @Contact(url = "XXX", name = "XXXX")))
public class SwaggerConfiguration extends SwaggerConfig {

}