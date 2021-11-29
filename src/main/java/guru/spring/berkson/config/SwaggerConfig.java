package guru.spring.berkson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Created by berkson
 * Date: 28/11/2021
 * Time: 11:46
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    public static final String CUSTOMER_TAG = "Serviço de Cliente";
    public static final String CATEGORY_TAG = "Serviço de Categorias";

    // essa é a configuração padrão do swagger, mesmo que não inclua o bean abaixo.
    // Caso deseje expor diferentes pontos da api deve realizar configuração mais granular.
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.metaData())
                .pathMapping("/")
                .tags(new Tag(CUSTOMER_TAG, "Controller de Clientes"))
                .tags(new Tag(CATEGORY_TAG, "Controller de Categorias"));
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Berkson Ximenes", "http://www.localhost.com.br",
                "berksonx@yahoo.com.br");
        return new ApiInfo("Aplicação para estudo de Spring Rest",
                "Aplicação para retirar dúvidas de configuração",
                "1.0",
                "termo_de_servico", contact,
                "Licença Apache Versão 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
    }
}
