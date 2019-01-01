package com.darian.darianSercurityCore.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class TimeAspect {
    private final String dateformat = "yyyy-MM-dd HH:mm:ss:SSS";
    private final ObjectMapper objectMapper = new ObjectMapper();
    /**
     * <br>方法说明 :定义controller层的切点
     * <br>作者：Darian
     **/
    @Pointcut("execution( * com.darian.*.controller..*(..))")
    public void controllerLog() {

    }

    @Around("controllerLog()")
    public Object aroudController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return doProceedingJoinPoint(proceedingJoinPoint);
    }

    public Object doProceedingJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
//        SimpleDateFormat fmt = new SimpleDateFormat(dateformat);
//        objectMapper.setDateFormat(fmt);
        // 为JoinPoint
        // 的子类，在父类基础上多了proceed()方法，用于增强切面

        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // java reflect相关类，通过反射得到注解
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();

        StringBuffer classAndMethod = new StringBuffer();

        // 获取类注解LogCustom
        LogCustom classAnnotation = targetClass.getAnnotation(LogCustom.class);
        // 获取方法注解LogCustom
        LogCustom methodAnnotation = method.getAnnotation(LogCustom.class);

        // 如果类上Log注解不为空，则执行proceed()
        if (classAnnotation != null) {
            if (classAnnotation.ignore()) {
                // proceed() 方法执行切面方法，并返回方法返回值
                return joinPoint.proceed();
            }
            classAndMethod.append(classAnnotation.value()).append("-");
        }

        // 如果方法上Log注解不为空，则执行proceed()
        if (methodAnnotation != null) {
            if (methodAnnotation.ignore()) {
                return joinPoint.proceed();
            }
            classAndMethod.append(methodAnnotation.value());
        }

        // 拼凑目标类名和参数名
        String target = targetClass.getName() + "#" + method.getName();

        String params = objectMapper.writeValueAsString(joinPoint.getArgs());


        log.info("\n{} 开始调用--> {} 参数:{}", classAndMethod.toString(), target, params);

        long start = System.currentTimeMillis();
        // 如果类名上和方法上都没有Log注解，则直接执行 proceed()
        Object result = joinPoint.proceed();
        long timeConsuming = System.currentTimeMillis() - start;

        String resultString = objectMapper.writeValueAsString(result);

        log.info("\n{} 调用结束<-- {} 返回值:{} 耗时:{}ms" , classAndMethod.toString(), target,
                resultString,
                timeConsuming);
        return result;
    }
}
