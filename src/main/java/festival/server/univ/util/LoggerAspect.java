package festival.server.univ.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {
    @Around("@annotation(CLogger)")
    public Object proceed(ProceedingJoinPoint joinPoint) throws  Throwable {
        final long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        final long timeTaken = System.currentTimeMillis() -startTime;
        log.info("[CLogger] class={}, method={}, executionTime={}ms",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName(),timeTaken);
        return result;    }
}
