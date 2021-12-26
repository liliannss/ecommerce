package br.com.ecommerce.user.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.webmvc.ui.SwaggerConfig;

@OpenAPIDefinition(
        info = @Info(
                title = "E-COMMERCE",
                description = "Projeto criado para fins de estudo - Conceitos e tecnologias que eventualmente irão " +
                        "auxiliar na implementação do desafio proposto",
                contact = @Contact(name = "liliannss", url = "https://github.com/liliannss/ecommerce")))
public class SwaggerConfiguration extends SwaggerConfig {

}