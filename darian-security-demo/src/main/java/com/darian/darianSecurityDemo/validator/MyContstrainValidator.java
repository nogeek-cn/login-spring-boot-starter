//package com.darian.darianSecurityDemo.validator;
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//@Slf4j
//public class MyContstrainValidator implements
//        ConstraintValidator<MyConstrait, Object> {
//
//    @Override
//    public void initialize(MyConstrait constraintAnnotation) {
//        log.info("MyContstrainValidator initialize ......");
//    }
//
//    @Override
//    public boolean isValid(Object value, ConstraintValidatorContext context) {
//        // 这里写验证的逻辑
//        log.info("MyContstrainValidator isvalid......");
//        return false;
//    }
//}
