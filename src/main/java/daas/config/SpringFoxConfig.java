package daas.config;

import java.time.LocalDate;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${spring.application.version}")
    private String version;

    @Bean
    public Docket pipeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .version(version)
                        .title(appName)
                        .description("[Service description]()" )
                        .build())
                .select()
                .apis(
                        or(
                                withClassAnnotation(RestController.class),
                                input -> Iterables.any(input.getPatternsCondition().getPatterns(), regex("/refresh"))
                        )
                ).paths(not(regex("/refresh.json")))
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("500 message")
                                .responseModel(new ModelRef("Error"))
                                .build()));

    }

}
