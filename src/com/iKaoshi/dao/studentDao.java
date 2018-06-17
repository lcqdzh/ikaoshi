package com.iKaoshi.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
 
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iKaoshi.bean.*;
public class studentDao {

	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
		  }
		 
	public List<Student> login(int id,String password){
		  String sql = "select * from ikaoshi.student where stu_id="+id+" and password="+password;
		  return jdbcTemplate.query(sql, new StudentMapper());
	  }
	class StudentMapper implements RowMapper<Student> {
		 
		  @Override
		  public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
		    Student student = new Student();
		    student.setId(rs.getInt(1));
		    student.setPassword(rs.getString(2)); 
		   // System.out.println(rs.getInt(1));
		   // System.out.println(rs.getInt(2));
		    return student;
		  }
		 
		  }
}
