package com.study.base.boot.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    //클래스위에 컨피규레이션을 달면 이 클래스 자체가 빈으로 등록되어 싱글톤으로 관리된다.
    //컴포넌트 아래 컨피규레이션이 들어있다. (서비스와 컨트롤러 아래도 들어있다.)
    //따라서 컨피규레이션을 설정파일에 많이 단다.

    @Bean
    public OpenAPI springOpenApi(){
        //빈을 달아주면 OpenApi가 스프링으로 관리된다.
        final var info = new Info()
                .title("Study API")
                .description("Study Rest API")
                .version("v1.0");

        return new OpenAPI().info(info);
    }

}
