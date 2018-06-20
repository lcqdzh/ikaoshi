package com.iKaoshi.dao;
// create by lcq
//教师dao
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
 
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Student;
import com.iKaoshi.bean.Teacher;
import com.iKaoshi.dao.studentDao.StudentMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class teacherDao {

	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
		  }
		 
	public List<Teacher> login(int id,String password){
		  String sql = "select * from ikaoshi.teacher where tea_Id="+id+" and password="+password;
		  return jdbcTemplate.query(sql, new TeacherMapper());
	  }
	
	class TeacherMapper implements RowMapper<Teacher> {
		 
		  @Override
		  public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
		    Teacher teacher = new Teacher();
		    teacher.setTea_ID(rs.getInt(1));
		    teacher.setPassword(rs.getString(2)); 
		   // System.out.println(rs.getInt(1));
		   // System.out.println(rs.getInt(2));
		    return teacher;
		  }
		 
		  }
	//更改密码
	//create by lcq 2018年6月20日19:41:14
	public boolean update_tea_password(int tea_Id,String password){
		String sql="update ikaoshi.teacher set password="+password+" where tea_Id="+tea_Id;
		return jdbcTemplate.update(sql)==1;
	}

	
}
