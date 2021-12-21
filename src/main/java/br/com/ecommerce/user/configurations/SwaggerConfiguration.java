package br.com.ecommerce.user.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.webmvc.ui.SwaggerConfig;

@OpenAPIDefinition(
        info = @Info(
                title = "Cadastro de Usuários",
                description = "API para Cadastro de Usuários",
                contact = @Contact(url = "XXX", name = "XXX")))
public class SwaggerConfiguration extends SwaggerConfig {

}