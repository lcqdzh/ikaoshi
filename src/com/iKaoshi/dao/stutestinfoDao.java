package com.iKaoshi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Student;
import com.iKaoshi.bean.Stutestinfo;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.dao.tikuDao.TikuxinxiMapper;

public class stutestinfoDao {

	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
		  }
	
	//查看考试信息是否已存在
	//create by lcq 2018年6月20日21:16:27
	public List<Stutestinfo> quarySti(int test_id,int stu_id){
		  String sql = "select * from ikaoshi.stu_test_info where test_Id="+test_id+" and stu_Id="+stu_id+";";
		  return jdbcTemplate.query(sql, new StutestinfoMapper());
	  }
	
	//添加考试信息
	//create by lcq 2018年6月15日22:46:56
	public boolean addStutestinfo(Stutestinfo s) {
		  String sql = "insert into ikaoshi.stu_test_info(stu_Id,test_Id) values(?,?)";
		  //System.out.println(2);
		 return jdbcTemplate.update(sql,
		    new Object[] { s.getStu_Id(),s.getTest_Id()}) == 1;
		  }
	//查看学生信息是否已存在
	//create by lcq 2018年6月20日21:19:42
	public List<Student> quaryStu(int stu_id){
		  String sql = "select * from ikaoshi.student where  stu_Id="+stu_id+";";
		  return jdbcTemplate.query(sql, new StudentMapper());
	  }
	//添加学生信息
	//create by lcq 2018年6月20日21:23:09
	public boolean addStudent(Student s) {
		  String sql = "insert into ikaoshi.student(stu_Id,password,name) values(?,?,?)";
		  //System.out.println(2);
		 return jdbcTemplate.update(sql,
		    new Object[] { s.getId(),s.getPassword(),s.getStu_name()}) == 1;
		  }
	//查找学生的信息
	//create by lcq 2018年6月20日22:10:33
	public List<Student> queryAllbytestid(int test_id)
	{
		String sql="SELECT DISTINCT student.stu_Id,student.password,student.name from ikaoshi.student student,ikaoshi.stu_test_info stu_test_info where student.stu_Id=stu_test_info.stu_Id and stu_test_info.test_Id="+test_id+";";
		return jdbcTemplate.query(sql, new StudentMapper());
	}
	
	
	
	
	class StutestinfoMapper implements RowMapper<Stutestinfo> {
		 
		  @Override
		  public Stutestinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			Stutestinfo s = new Stutestinfo();
		    s.setStu_Id(rs.getInt(1));
		    s.setTest_Id(rs.getInt(2));
		   // System.out.println(rs.getInt(1));
		   // System.out.println(rs.getInt(2));
		    return s;
		  }
		 
		  }
	class StudentMapper implements RowMapper<Student> {
		 
		  @Override
		  public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			  Student s = new Student();
		    s.setId(rs.getInt(1));
		    s.setStu_name(rs.getString(3));
		    //s.setTest_Id(rs.getInt(2));
		   // System.out.println(rs.getInt(1));
		   // System.out.println(rs.getInt(2));
		    return s;
		  }
		 
		  }
}
