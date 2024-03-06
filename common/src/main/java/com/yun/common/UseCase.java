package com.yun.common;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface UseCase {
    //역할을 표시하는 목적일 뿐 별도의 다른 동작은 하지 않는다
    @AliasFor(annotation = Component.class)
    String value() default "";
}
