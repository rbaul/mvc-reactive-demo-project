package springmvcdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springmvcdemo.web.controlles.StoreController;

@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Store Documentation")
                .description("Store API Documentation")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(StoreController.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build();
    }
}