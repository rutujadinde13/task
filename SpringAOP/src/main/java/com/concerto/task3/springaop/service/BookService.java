package com.concerto.task3.springaop.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.concerto.task3.springaop.entity.Book;

@Component
public class BookService {

	
	@Autowired
	private JdbcTemplate template;
	
	
	public boolean insertBook(Book book) throws Exception
	{
		System.out.println("data is inserting....");
		String sql="insert into Book values(?,?,?)";
		try
		{
		this.template.update(sql,book.getBookId(),book.getBookName(),book.getPrice());
		}catch (Exception e) 
		{
			
			throw new Exception("Book With"+ book.getBookId() +"already exists");
		}
		return true;
	}
	
	public List<Book> getAllBooks()
	{
		String sql="select * from Book";
		return this.template.query(sql, new BookRowMapper());
		
	}
	
	
	
	class BookRowMapper implements RowMapper<Book>
	{

		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Book book=new Book();
			
			book.setBookId(rs.getInt(1));
			book.setBookName(rs.getString(2));
			book.setPrice(rs.getFloat(3));
			return book;
		}
		
	}
}
