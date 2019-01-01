package com.darian.darianSecurityDemo.validator;

import com.darian.darianSecurityDemo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
@RequiredArgsConstructor
public class MyContstrainValidator implements
        ConstraintValidator<MyConstrait, Object> {
    private final UserService userServiceImpl;

    @Override
    public void initialize(MyConstrait constraintAnnotation) {
        log.info("MyContstrainValidator initialize ......");
    }

    /***
     *
     * @param value   加注解的属性的值
     * @param context
     * @return  // return true 校验通过  false 校验失败
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        // 这里写验证的逻辑
        log.info("MyContstrainValidator isvalid......");
        // userServiceImpl.....
        return false;
    }
}
