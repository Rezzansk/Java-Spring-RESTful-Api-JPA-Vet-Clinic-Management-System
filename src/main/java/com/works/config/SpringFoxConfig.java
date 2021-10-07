package com.works.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {

        SecurityReference securityReference = SecurityReference.builder()
                .reference("basicAuth")
                .scopes(new AuthorizationScope[0])
                .build();

        ArrayList<SecurityReference> reference = new ArrayList<>(1);
        reference.add(securityReference);

        ArrayList<SecurityContext> securityContexts = new ArrayList<>(1);
        securityContexts.add(SecurityContext.builder().securityReferences(reference).build());

        ArrayList<SecurityScheme> auth = new ArrayList<>(1);
        auth.add(new BasicAuth("basicAuth"));

        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(auth)
                .securityContexts(securityContexts)
                .select()
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Veteriner - Rest Api",
                "Veteriner Projesi İçin Geliştirilmiş Rest Api \n\n Örnek Kullanıcı: \n | Name: rezzan.sk@hotmail.com \n | Pass: 12",
                "API 1.0",
                "Terms of service",
                new Contact("Rezzan Işık", "www.veterinerim.com", "rezzan.sk@mail.com"),
                "License of API", "http://google.com.tr", Collections.emptyList());
    }


}