package com.concerto.task3.springaop;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import com.concerto.task3.springaop.entity.Book;
import com.concerto.task3.springaop.service.BookService;

/**
 * Hello world!
 *
 */

@Configuration
@ComponentScan
@PropertySource("classpath:database.properties")
@EnableAspectJAutoProxy
public class App 
{
	
	
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(App.class);
    	
    	BookService bookService=context.getBean(BookService.class);
    	
    	Book bookData=new Book(4, "HARRY POTTER-4", 670.00);
    	System.out.println(bookService.insertBook(bookData));
    	
    	for(Book books:bookService.getAllBooks())
    		System.out.println(books);
    }
    
    @Value("${ds.driverClassName}")
	private String driver;
	@Value("${ds.url}")
	private String url;
	@Value("${ds.user}")
	private String username;
	@Value("${ds.password}")
	private String password;
	
    @Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource ds=new DriverManagerDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		System.out.println(ds.getUsername());
		System.out.println(ds.getUrl());
		return ds;
		
	}
	
	@Bean
	public JdbcTemplate template(DataSource ds)
	{
		System.out.println("template creating......");
		return new JdbcTemplate(ds);
	}
}
