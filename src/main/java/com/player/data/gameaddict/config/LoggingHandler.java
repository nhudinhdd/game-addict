package com.player.data.gameaddict.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingHandler {
    private final String apiPointCut = ("within(com.player.data.gameaddict.controller..*)");

    @Pointcut(apiPointCut)
    public void logController() {
    }

    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
    }


    @Around("logController())")
    public Object beforeServiceAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        log.info(joinPoint.getSignature().getDeclaringTypeName().
                replace("com.player.data.gameaddict.controller.", "") + "." + joinPoint.getSignature().getName()
                + " => INPUT:" + Arrays.toString(joinPoint.getArgs()));
//        if (!authenticationUtil.authenticate(request)){
//            ResponseMetaData result = new ResponseMetaData(new MetaDTO(MetaData.UNAUTHORIZED));
//            ObjectMapper objectMapper = new ObjectMapper();
//            log.info(joinPoint.getSignature().getDeclaringTypeName().replace("phillip.poems.mobile.server.controller.", "")
//                    + "." + joinPoint.getSignature().getName() + " => RESULT:[" + objectMapper.writeValueAsString(result) + "]");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//        }

        return joinPoint.proceed();
    }

    @AfterReturning(pointcut = "logController() && allMethod()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info(joinPoint.getSignature().getDeclaringTypeName().replace("com.player.data.gameaddict.controller.", "")
                + "." + joinPoint.getSignature().getName() + " => RESULT:[" + objectMapper.writeValueAsString(result) + "]");
    }
}