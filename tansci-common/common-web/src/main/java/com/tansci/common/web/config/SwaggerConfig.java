package com.tansci.common.web.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName： SwaggerConfig.java
 * @ClassPath： com.tansci.common.web.config.SwaggerConfig.java
 * @Description： Swagger配置
 * @Author： tanyp
 * @Date： 2022/2/14 16:34
 **/
@Configuration
@EnableSwagger2
@EnableKnife4j
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tansci")) // 这里指定Controller扫描包路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Tansci Cloud API文档")
                .description("API文档")
                .contact(new Contact("tanyp", "http://typ1805.gitee.io", "typ1805@qq.com"))
                .version("1.0.0")
                .build();
    }

}
