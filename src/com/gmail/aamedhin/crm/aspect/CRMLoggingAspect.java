package com.gmail.aamedhin.crm.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	//setup logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	//setup pointcut declarations
		//for controller
	@Pointcut("execution(* com.gmail.aamedhin.crm.controller.*.*(..))") //any class, any method and any number of arguments
	private void forControllerPackage(){}
	
		//for service
	@Pointcut("execution(* com.gmail.aamedhin.crm.service.*.*(..))") //any class, any method and any number of arguments
	private void forServicePackage(){}
	
		//for service
	@Pointcut("execution(* com.gmail.aamedhin.crm.dao.*.*(..))") //any class, any method and any number of arguments
	private void forDaoPackage(){}
	
		//combine the above pointcut declarations 
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forCRMFlow(){}
	
	
	//add @Before advice
	@Before("forCRMFlow()")
	public void before(JoinPoint joinPoint){
		
		//display the method we are calling
		String method = joinPoint.getSignature().toString();
		logger.info("======>> in @Before: calling method: " + method);
		
		//display the arguments to the method
			//get arguments
		Object[] args = joinPoint.getArgs();
			//display args
		for(Object arg: args){
			logger.info("=======>> argument: " + arg);
		}
	}
	
	//add @AfterReturning advice
	@AfterReturning(
			pointcut="forCRMFlow()",
			returning="result"
			)
	public void afterReturning(JoinPoint joinPoint, Object result){
		
		//display method that we are returning fron
		String method = joinPoint.getSignature().toString();
		logger.info("======>> in @AfterReturning: from method: " + method);
		
		//display data returned
		logger.info("======>> result: " + result);
		
	}
	

}
