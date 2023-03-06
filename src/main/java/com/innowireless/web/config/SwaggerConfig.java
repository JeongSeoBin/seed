package com.innowireless.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Import(BeanValidatorPluginsConfiguration.class) => FIXME: VO 내에 설정되있는 @NotNull, @NotEmpty 등을 표시해주는 역할 [확인 필요]
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI apiInfo() {
        final Contact contact = new Contact();
        contact.setName("Innowireless Co.,Ltd.");
        contact.setUrl("http://www.innowireless.com");
        contact.setEmail("jungeol.park@innowireless.com");

        return new OpenAPI()
            .info(new Info().title("Web REST API")
                    .description("Web REST API with Swagger")
                    .version("v1.0.0")
                    .contact(contact)
                // .license(new License().name("Apache 2.0").url("http://springdoc.org"))
            )
            // .externalDocs(new ExternalDocumentation()
            //     .description("SpringShop Wiki Documentation")
            //     .url("https://springshop.wiki.github.org/docs"))
            ;
    }
}
