package com.darian.core;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({AutoConfiguration.class})
@Documented
@Inherited
public @interface EnableDarianSecurityCore {
}
