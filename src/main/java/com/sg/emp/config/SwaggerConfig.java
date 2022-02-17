package com.sg.emp.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.sg.emp"))
				.paths(regex("/emp.*"))
				.build();
	}

	/*
	 * private ApiInfo apiEndPointsInfo() { return new
	 * ApiInfoBuilder().title("Employeet REST API")
	 * .description("Employee information for Society Generale ") .contact(new
	 * Contact("Suresh Dash", "", "sureshdash4@gmail.com")).license("Apache 2.0")
	 * .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version(
	 * "1.0.0").build(); }
	 */
}
