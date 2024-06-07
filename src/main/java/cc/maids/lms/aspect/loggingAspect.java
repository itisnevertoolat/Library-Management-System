package cc.maids.lms.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class loggingAspect {
    @AfterReturning(value = "execution(public * cc.maids.lms.controller.*.*(..))")
    public void logAfter(JoinPoint joinPoint){
        log.debug("The Method {} has returned successfully ", joinPoint.getSignature().getName());


    }
    @AfterThrowing(value = "execution(public * cc.maids.lms.service.*.*(..))", throwing = "ex")
    public void logAfterException(JoinPoint joinPoint , Exception ex){
        log.error("Method {} throws an Exception: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}
