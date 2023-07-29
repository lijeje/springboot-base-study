package com.study.base.boot.config.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface RestApi {
    //어느시점에다 적용할지 어디에 쓸지 정해야함.
    //리텐션은 시점
    //documented 를 붙이면 document를 따서 보여줌.
    //실제로 이게 리퀘스트 맵핑 역할을 해야하니 거기서 사용하는 필드를 가져와야함.
    //웨에 선언된 RequestMapping은 스프링에서 얘를 잡아오게 하기 위해서 . 그리고 내용을 가져온 이유는 실제로 RequestMapping역할을 하게 하기 위해서
    String name() default "";

    @AliasFor("path")
    String[] value() default {};

    @AliasFor("value")
    String[] path() default {};

    RequestMethod[] method() default {};

    String[] params() default {};

    String[] headers() default {};

    String[] consumes() default {};

    String[] produces() default {};
}
