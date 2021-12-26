package br.com.ecommerce.user.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.webmvc.ui.SwaggerConfig;

@OpenAPIDefinition(
        info = @Info(
                title = "E-COMMERCE",
                description = "XXX",
                contact = @Contact(url = "XXX", name = "XXX")))
public class SwaggerConfiguration extends SwaggerConfig {

}