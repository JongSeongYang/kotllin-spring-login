package com.example.kotlinTemplate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {

    lateinit var version: String

    @Bean
    fun productApi(): Docket {
        return Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .securityContexts(listOf(securityContext()))
                .securitySchemes(listOf(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.kotlinTemplate"))
                .paths(PathSelectors.any())
                .build()
    }

    @Bean
    fun productApiV1(): Docket {
        version = "V1"
        return Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo(version))
                .groupName(version)
                .securityContexts(listOf(securityContext()))
                .securitySchemes(listOf(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.kotlinTemplate"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
    }

    fun apiInfo() : ApiInfo {
        return ApiInfoBuilder()
                .title("API")
                .description("API")
                .version("0.0.1")
                .termsOfServiceUrl("http://hiandd.com")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .extensions(Collections.emptyList())
                .contact(
                        Contact(
                                "JongSeong",
                                "http://baegoon.kr",
                                "jsyang@hiandd.com"
                        )
                )
                .build();
    }

    fun apiInfo(version:String) : ApiInfo {
        return ApiInfoBuilder()
                .title("API "+ version)
                .description("API")
                .version(version)
                .termsOfServiceUrl("http://hiandd.com")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .extensions(Collections.emptyList())
                .contact(
                        Contact(
                                "JongSeong",
                                "http://baegoon.kr",
                                "jsyang@hiandd.com"
                        )
                )
                .build();
    }


    fun apiKey(): ApiKey {
        return ApiKey("Authorization", "jwt", "header")
    }

    fun securityContext():SecurityContext {
        return SecurityContext.builder().securityReferences(listOf(defaultAuth())).build()
    }


    fun defaultAuth(): SecurityReference =
            SecurityReference("Authorization", arrayOf(AuthorizationScope("global", "accessEverything")))
}