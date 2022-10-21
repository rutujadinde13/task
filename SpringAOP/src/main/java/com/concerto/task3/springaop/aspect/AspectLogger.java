package com.concerto.task3.springaop.aspect;



import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.concerto.task3.springaop.App;
import com.concerto.task3.springaop.entity.Book;

@Aspect
@Component
public class AspectLogger {

	private static Logger logger=Logger.getLogger(App.class);
	
	@Pointcut("execution(* com.concerto.task3.springaop.service.BookService.insertBook(..))")
	public void insertBookData()
	{
		
	}
	
	@Pointcut("execution(* com.concerto.task3.springaop.service.BookService.getAllBooks(..))")
	public void getAllBookData()
	{
		
	}
	
	@Before("insertBookData()")
	public void beforeAdvice()
	{
		logger.debug("before advice logger");
		
	}
	
	@After("insertBookData()")
	public void afterAdvice()
	{
		logger.debug("after advice logger");
	}
	
	
	@AfterReturning(pointcut = "getAllBookData()",returning = "books")
	public void getBooks(List<Book> books) {
		logger.info(books);
		logger.info("getting books data");
	}
	
	@AfterThrowing(pointcut="insertBookData()",throwing="ex")
	public void afterThrowingAdvice(Exception ex)
	{
		
		logger.error("after rethrowing the exception "+ex.getMessage());
		
	}
	
	@Around("insertBookData()")
	public boolean around(ProceedingJoinPoint pjp) {
		
		boolean data = true;
		logger.info("around advice before");
		try {
			data = (boolean) pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		logger.info("around advice after");
		return data;
	}
	
}
