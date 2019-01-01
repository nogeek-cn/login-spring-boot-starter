package com.darian.darianSecurityDemo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 *
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MyContstrainValidator.class})  // 这个注解用的是某一个类的校验逻辑
public @interface MyConstrait {

    String message() default "MyConstrait default message";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
