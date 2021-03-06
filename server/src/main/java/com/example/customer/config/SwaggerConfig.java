package com.example.customer.config;


import com.google.common.base.Predicates;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;
/**
 * @author Raghavendra
 */

@Log4j2
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerApi() {
        log.info("swaggerApi has been started configuring");
        String contactName = "Customer Shipping Application";
        String contactEmail = "Raghavendra@gmail.com";
        Contact contact = new Contact(contactName, "", contactEmail);
        ApiInfo apiInfo = new ApiInfoBuilder().version("1.0")
                .description("Customer Shipping Application which provide an option to end customer to save and retrieve the customer information in the database")
                .title("Customer Shipping Service Application").version("1.0.0")
                .license("Commercial")
                .contact(contact).build();
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json");
        consumes.add("text/html");
        Set<String> produces = new HashSet<>();
        produces.add("application/json");
        produces.add("text/html");
        log.info("swaggerApi details Title " + apiInfo.getTitle() + "version " + apiInfo.getVersion());
        log.info("swaggerApi has been configuring successfully");
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(produces)
                .consumes(consumes)
                .apiInfo(apiInfo)
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error"))).build()
                ;
    }
}
