package com.iKaoshi.dao;
//create by lcq
//题库Dao
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Student;
import com.iKaoshi.bean.Teacher;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.dao.studentDao.StudentMapper;
import com.iKaoshi.dao.teacherDao.TeacherMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class tikuDao {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
		  }
	
	//查看题库信息
	//create by lcq 2018年6月16日08:41:13
	public List<Tikuxinxi> quary(int tea_id){
		  String sql = "select * from ikaoshi.tea_tiku where tea_Id="+tea_id;
		  return jdbcTemplate.query(sql, new TikuxinxiMapper());
	  }
	
	//添加题库信息
	//create by lcq 2018年6月15日22:46:56
	public boolean addTikuxinxi(Tikuxinxi t) {
		  String sql = "insert into ikaoshi.tea_tiku(tea_Id,tiku_name) values(?,?)";
		  //System.out.println(2);
		 return jdbcTemplate.update(sql,
		    new Object[] { t.getTea_Id(),t.getTiku_name()}) == 1;
		  }
	
	
	
	
	
	
	class TikuxinxiMapper implements RowMapper<Tikuxinxi> {
		 
		  @Override
		  public Tikuxinxi mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
		    Tikuxinxi t = new Tikuxinxi();
		    t.setTiku_ID(rs.getInt(1));
		    t.setTiku_name(rs.getString(3));
		   // System.out.println(rs.getInt(1));
		   // System.out.println(rs.getInt(2));
		    return t;
		  }
		 
		  }
}
