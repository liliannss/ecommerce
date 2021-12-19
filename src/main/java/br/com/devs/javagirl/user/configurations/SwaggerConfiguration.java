package br.com.devs.javagirl.user.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.webmvc.ui.SwaggerConfig;

@OpenAPIDefinition(
        info = @Info(
                title = "Cadastro de Usuários",
                description = "API para Cadastro de Usuários",
                contact = @Contact(url = "https://www.linkedin.com/in/devsjavagirlbr/?originalSubdomain=br", name = "Devs Java Girl")))
public class SwaggerConfiguration extends SwaggerConfig {

}