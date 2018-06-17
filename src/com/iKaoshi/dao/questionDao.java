package com.iKaoshi.dao;
//create lcq
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Question;
import com.iKaoshi.bean.Student;
import com.iKaoshi.bean.Teacher;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.dao.studentDao.StudentMapper;
import com.iKaoshi.dao.teacherDao.TeacherMapper;
import com.iKaoshi.dao.tikuDao.TikuxinxiMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class questionDao {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
	}
	//根据题库编号、试题编号修改
	//create by lcq 2018年6月17日14:54:19
	public boolean updatebyone(Question q) {
	  String sql = "update ikaoshi.question set question_content=?,question_type=?,choice_A=?,choice_B=?,"
	+ "choice_C=?,choice_D=?,answer=?,question_level=? where question_Id=? and tiku_Id=?;";
	
	 return jdbcTemplate.update(sql,new Object[] {q.getQuestion_content(),q.getQuestion_type(),q.getChoice_A(),q.getChoice_B(),
			 q.getChoice_C(),q.getChoice_D(),q.getAnswer(),q.getLable(),q.getQuestion_Id(),q.getTiku_Id()}) == 1;
	  }
	//根据题库的编号获取获取试题号的最大值
	//create by lcq 2018年6月17日15:30:26
	@SuppressWarnings("deprecation")
	public int getMaxquestionidBytikuid(int tiku_Id)
	{
		return jdbcTemplate.queryForInt("SELECT MAX(question_Id) FROM ikaoshi.question where tiku_Id="+tiku_Id+";");
	}
	//根据试题号与题库号添加到题库中
	//create by lcq 2018年6月17日16:56:07
	public boolean addQuestion(Question q) {
	String sql = "insert into ikaoshi.question(question_Id,tiku_Id,question_content,question_type,choice_A,choice_B,choice_C,choice_D,answer,question_level)"
			+ " values(?,?,?,?,?,?,?,?,?,?)";
	//System.out.println(2);
	return jdbcTemplate.update(sql,
	new Object[] { q.getQuestion_Id(),q.getTiku_Id(),q.getQuestion_content(),q.getQuestion_type(),q.getChoice_A(),q.getChoice_B(),q.getChoice_C(),q.getChoice_D(),q.getAnswer(),q.getLable()}) == 1;
	}
	//根据试题号与题库号删除某一道题
	//create by lcq 2018年6月17日17:23:58
	public boolean delateQuestion(int question_Id,int tiku_Id) {
		String sql = "delete from ikaoshi.question where question_Id=? and tiku_Id=?";
		return jdbcTemplate.update(sql,
		    new Object[] {question_Id,tiku_Id}) == 1;
	}
	//根据题库编号查询题库
	//create by 2018年6月16日20:54:48
	public List<Question> quarybytikuid(int tiku_Id){
		  String sql = "select * from ikaoshi.question where tiku_Id="+tiku_Id;
		  System.out.println(sql);
		  return jdbcTemplate.query(sql, new QuestionMapper());
	  }
	public List<Question> quarybyquestionIdandtikuId(int question_Id,int tiku_Id){
		  String sql = "select * from ikaoshi.question where question_Id="+question_Id+" and tiku_Id="+tiku_Id;
		  System.out.println(sql);
		  return jdbcTemplate.query(sql, new QuestionMapper());
	  }
	class QuestionMapper implements RowMapper<Question> {
		 
		  @Override
		  public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			Question q = new Question();
		    q.setQuestion_Id(rs.getInt(1));
		    q.setTiku_Id(rs.getInt(2));
		    q.setQuestion_content(rs.getString(3));
		    q.setQuestion_type(rs.getInt(4));
		    q.setChoice_A(rs.getString(5));
		    q.setChoice_B(rs.getString(6));
		    q.setChoice_C(rs.getString(7));
		    q.setChoice_D(rs.getString(8));
		    q.setAnswer(rs.getInt(9));
		    q.setLable(rs.getInt(10));
		    
		    System.out.println(rs.getString(2));
		   // System.out.println(rs.getInt(2));
		    return q;
		  }
		 
		  }
}
