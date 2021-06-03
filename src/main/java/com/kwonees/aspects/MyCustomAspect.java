package com.kwonees.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.kwonees.dao.DaoException;

@Aspect
@Component
public class MyCustomAspect {
	public MyCustomAspect() {
		System.out.println("MyCustomAspect is instantiated!");
	}
	
	@Before("execution(* com.kwonees.dao.ProductDao.*(..))")
	public void logBefore(JoinPoint jp) {
		System.out.println("Before executing " + jp.getSignature().getName());
		System.out.println("Arguments are " + Arrays.toString(jp.getArgs()));
		
	}
	@Around("execution(* com.kwonees.dao.ProductDao.*(Double, Double))")
	public Object swapInputs(ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Double min = (Double) args[0];
		Double max = (Double) args[1];
		if (min > max) 
			args = new Object[] {max, min};
		return pjp.proceed(args);
	}
	
	@AfterThrowing(throwing="t", pointcut="execution(* com.kwonees..*Dao.*(..))")
	public void convertToDaoException(Throwable t) throws DaoException {
		throw new DaoException(t);
	}
	
//	@After("execution(* com.kwonees.dao.ProductDao.*(..))")
//	public void logAfter() {
//		System.out.println("Logging after target method execution");
//	}

}
