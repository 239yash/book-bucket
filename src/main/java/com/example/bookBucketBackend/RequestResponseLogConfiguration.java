package com.example.bookBucketBackend;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class RequestResponseLogConfiguration {
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void logBeforePostMapping(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg != null) {
                if (Arrays
                        .stream(((MethodSignature) joinPoint.getSignature()).getMethod().getParameterAnnotations())
                        .flatMap(Arrays::stream)
                        .anyMatch(annotation -> annotation.annotationType().equals(RequestBody.class))) {
                    log.info("Request Body(Logged in Aspect) - " + arg);
                }
            }
        }
    }
}
