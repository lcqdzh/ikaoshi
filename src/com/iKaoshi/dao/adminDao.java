package com.iKaoshi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Student;
import com.iKaoshi.bean.Teacher;
import com.iKaoshi.dao.studentDao.StudentMapper;

public class adminDao {
	//建立连接
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
	}
	/**
	 * 管理员登陆查询
	 * @param id
	 * @param password
	 * @return
	 */
	public List<Student> login(int id,String password){
		  String sql = "select * from admin where admin_Id='"+id+"'  and password='"+password+"'";
		  return jdbcTemplate.query(sql, new StudentMapper());
	}
	/**
	 * 返回管理员用户名
	 * @param stu_Id
	 * @return
	 */
	public String get_admin_name(int stu_Id){
		String sql="select admin_name from admin where admin_Id="+stu_Id;
		return (String)jdbcTemplate.queryForObject(sql,java.lang.String.class);
	}
	/**
	 * 返回管理员密码
	 * @param admin_Id
	 * @return
	 */
	public String get_admin_password(int admin_Id){
		String sql="select password from admin where admin_Id="+admin_Id;
		return (String)jdbcTemplate.queryForObject(sql,java.lang.String.class);
	}
	/**
	 * 查询所有学生信息列表
	 * @param id
	 * @param password
	 * @return
	 */
	public List<Student> stu_list(){
		  String sql = "select * from student order by stu_Id";
		  return jdbcTemplate.query(sql, new StudentMapper());
	}
	
	/**
	 * 查询所有老师信息列表
	 * @return
	 */
	public List<Teacher> tea_list(){
		  String sql = "select * from teacher order by tea_Id";
		  return jdbcTemplate.query(sql, new teacherMapper());
	}
	/**
	 * 更改学生密码
	 * @param stu_Id
	 * @return
	 */
	public boolean update_stu_password(int stu_Id,String password){
		String sql="update student set password="+password+" where stu_Id="+stu_Id;
		return jdbcTemplate.update(sql)==1;
	}
	/**
	 * 更改老师密码
	 * @param stu_Id
	 * @return
	 */
	public boolean update_tea_password(int tea_Id,String password){
		String sql="update teacher set password="+password+" where tea_Id="+tea_Id;
		return jdbcTemplate.update(sql)==1;
	}
	/**
	 * 更改管理员密码
	 * @param stu_Id
	 * @return
	 */
	public boolean update_admin_password(int admin_Id,String password){
		String sql="update admin set password="+password+" where admin_Id="+admin_Id;
		return jdbcTemplate.update(sql)==1;
	}
	/**
	 * 删除老师信息
	 * @param tea_Id
	 * @return
	 */
	public boolean delete_tea(int tea_Id){
		String sql="delete from teacher where tea_Id="+tea_Id;
		return jdbcTemplate.update(sql)==1;
	}
	/**
	 * 删除学生信息
	 * @param stu_Id
	 * @return
	 */
	public boolean delete_stu(int stu_Id){
		String sql="delete from student where stu_Id="+stu_Id;
		return jdbcTemplate.update(sql)==1;
	}
	/**
	 * 在学生表中插入学生信息
	 * @param s
	 * @return
	 */
	public boolean add_student(Student s) {
		  String sql = "insert into student(stu_Id,password,name) values(?,?,?)";
		  //System.out.println(2);
		 return jdbcTemplate.update(sql,new Object[] { s.getId(),s.getPassword(),s.getStu_name()}) == 1;
	}
	/**
	 * 查询学生信息是否已存在
	 * @param stu_id
	 * @return
	 */
	public List<Student> query_stu(int stu_id){
		  String sql = "select * from student  where  stu_Id="+stu_id+";";
		  return jdbcTemplate.query(sql, new StudentMapper());
	  }
	/**
	 * 在学生表中插入老师信息
	 * @param s
	 * @return
	 */
	public boolean add_tea(Teacher tea) {
		  String sql = "insert into teacher(tea_Id,password,tea_name) values(?,?,?)";
		  //System.out.println(2);
		 return jdbcTemplate.update(sql,new Object[] { tea.getTea_ID(),tea.getPassword(),tea.getTea_name()}) == 1;
	}
	/**
	 * 查询老师信息是否已存在
	 * @param stu_id
	 * @return
	 */
	public List<Teacher> query_tea(int tea_Id){
		  String sql = "select * from teacher  where  tea_Id="+tea_Id+";";
		  return jdbcTemplate.query(sql, new teacherMapper());
	  }


	
	
	
	
	
	
	
	
	/**
	 * 将学号密码查询结果装入teacher的List中
	 * @author perfect
	 *
	 */
	class teacherMapper implements RowMapper<Teacher> {
		 
		  @Override
		  public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			  
			Teacher teacher=new Teacher();
			teacher.setTea_ID(rs.getInt(1));
			teacher.setPassword(rs.getString(2));
			teacher.setTea_name(rs.getString(3));		   
		    return teacher;
		  }
		 
	}
	
	
	/**
	 * 将学号密码查询结果装入student的List中
	 * @author perfect
	 *
	 */
	class StudentMapper implements RowMapper<Student> {
		 
		  @Override
		  public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
		    Student student = new Student();
		    student.setId(rs.getInt(1));
		    student.setPassword(rs.getString(2)); 
		    student.setStu_name(rs.getString(3));
		    return student;
		  }
		 
	}
	
	
		
}
