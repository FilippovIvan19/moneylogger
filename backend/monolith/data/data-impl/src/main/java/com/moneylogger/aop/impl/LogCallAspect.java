package com.moneylogger.aop.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component @Aspect
public class LogCallAspect {
    @Around("@annotation(com.moneylogger.aop.api.LogCall)")
    public Object addLogging (ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
        Object[] argVals = joinPoint.getArgs();
        String[] argNames = methodSig.getParameterNames();
        String[] argStrings = new String[argVals.length];

        for (int i = 0; i < argVals.length; i++) {
            argStrings[i] = argNames[i] + " = " + argVals[i];
        }
        String argsAsString = String.join(", ", argStrings);
        log.debug("Invoking {} with params: {}", methodSig.getName(), argsAsString);

        Object retVal = joinPoint.proceed();

        log.debug("Invocation of {} returned {}", methodSig.getName(), argValToString(retVal));
        return retVal;
    }


    private static String argValToString(Object argVal) {
        return argVal.toString(); // todo add processing for arrays and lists?
    }
}
