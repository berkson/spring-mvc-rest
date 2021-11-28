package guru.spring.berkson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Created by berkson
 * Date: 28/11/2021
 * Time: 11:46
 */
@Configuration
public class SwaggerConfig {

    // essa é a configuração padrão do swagger, mesmo que não inclua o bean abaixo.
    // Caso deseje expor diferentes pontos da api deve realizar configuração mais granular.
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.metaData())
                .pathMapping("/");
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Berkson Ximenes", "http://www.localhost.com.br",
                "berksonx@yahoo.com.br");
        return new ApiInfo("Spring Study Application",
                "Application for Students get hid of doubts!",
                "1.0",
                "term_of_service", contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }
}
