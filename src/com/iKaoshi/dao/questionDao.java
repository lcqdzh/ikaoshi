package com.iKaoshi.dao;
//create lcq
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Question1;
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
	//将抽到的提存入到shijuan表中
	//create by lcq 2018年6月24日20:39:29
	public boolean insertShijuan(int test_id,int tiku_id,int question_id) {
	String sql = "insert into ikaoshi.shijuan(test_Id,tiku_Id,question_Id)"
			+ " values(?,?,?)";
	//System.out.println(2);
	return jdbcTemplate.update(sql,
	new Object[] { test_id,tiku_id,question_id}) == 1;
	}
	
	
	//根据题库号获取单选简单个数
	//create by lcq 2018年6月21日15:38:52
	@SuppressWarnings("deprecation")
	public int getDx_easy(int tiku_Id) {
		String sql="select count(question.question_Id) from ikaoshi.question where  question.question_level=1 and question_type=1 and tiku_Id="+tiku_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	//根据题库号获取单选medium个数
	//create by lcq 2018年6月21日15:38:52
	@SuppressWarnings("deprecation")
	public int getDx_medium(int tiku_Id) {
		String sql="select count(question.question_Id) from ikaoshi.question where  question.question_level=2 and question_type=1 and tiku_Id="+tiku_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	//根据题库号获取单选hard个数
	//create by lcq 2018年6月21日15:38:52
	@SuppressWarnings("deprecation")
	public int getDx_hard(int tiku_Id) {
		String sql="select count(question.question_Id) from ikaoshi.question where  question.question_level=3 and question_type=1 and tiku_Id="+tiku_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	//根据题库号获取判断简单个数
	//create by lcq 2018年6月21日15:38:52
	@SuppressWarnings("deprecation")
	public int getPd_easy(int tiku_Id) {
		String sql="select count(question.question_Id) from ikaoshi.question where  question.question_level=1 and question_type=2 and tiku_Id="+tiku_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	//根据题库号获取判断中等个数
	//create by lcq 2018年6月21日15:38:52
	@SuppressWarnings("deprecation")
	public int getPd_medium(int tiku_Id) {
		String sql="select count(question.question_Id) from ikaoshi.question where  question.question_level=2 and question_type=2 and tiku_Id="+tiku_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	//根据题库号获取判断苦难个数
	//create by lcq 2018年6月21日15:38:52
	@SuppressWarnings("deprecation")
	public int getPd_hard(int tiku_Id) {
		String sql="select count(question.question_Id) from ikaoshi.question where  question.question_level=3 and question_type=2 and tiku_Id="+tiku_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	//根据题库号获取单选简单个数
	//create by lcq 2018年6月21日15:38:52
	@SuppressWarnings("deprecation")
	public int getDt_easy(int tiku_Id) {
		String sql="select count(question.question_Id) from ikaoshi.question where  question.question_level=1 and question_type=3 and tiku_Id="+tiku_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	//根据题库号获取单选简单个数
	//create by lcq 2018年6月21日15:38:52
	@SuppressWarnings("deprecation")
	public int getDt_medium(int tiku_Id) {
		String sql="select count(question.question_Id) from ikaoshi.question where  question.question_level=2 and question_type=3 and tiku_Id="+tiku_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	//根据题库号获取单选简单个数
	//create by lcq 2018年6月21日15:38:52
	@SuppressWarnings("deprecation")
	public int getDt_hard(int tiku_Id) {
		String sql="select count(question.question_Id) from ikaoshi.question where  question.question_level=3 and question_type=3 and tiku_Id="+tiku_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	//根据题库编号、试题编号修改
	//create by lcq 2018年6月17日14:54:19
	public boolean updatebyone(Question1 q) {
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
	//create by lcq 2018年6月19日01:36:17
	public boolean addQuestionkg(int stu_id,int test_id,int question_id,int tiku_id) {
	String sql = "insert into ikaoshi.shijuan_keguan(stu_Id,test_Id,question_Id,tiku_Id)"
			+ " values(?,?,?,?)";
	//System.out.println(2);
	return jdbcTemplate.update(sql,
	new Object[] { stu_id,test_id,question_id,tiku_id}) == 1;
	}
	//create by lcq 2018年6月19日01:27:13
	public boolean addQuestionzg(int stu_id,int test_id,int question_id,int tiku_id) {
		String sql = "insert into ikaoshi.shijuan_zhuguan(stu_Id,test_Id,question_Id,tiku_Id)"
				+ " values(?,?,?,?)";
		//System.out.println(2);
		return jdbcTemplate.update(sql,
		new Object[] { stu_id,test_id,question_id,tiku_id}) == 1;
		}
	//根据试题号与题库号添加到题库中
	//create by lcq 2018年6月17日16:56:07
	public boolean addQuestion(Question1 q) {
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
	public List<Question1> quarybytikuid(int tiku_Id){
		  String sql = "select * from ikaoshi.question where tiku_Id="+tiku_Id;
		  System.out.println(sql);
		  return jdbcTemplate.query(sql, new QuestionMapper());
	  }
	public List<Question1> quarybyquestionIdandtikuId(int question_Id,int tiku_Id){
		  String sql = "select * from ikaoshi.question where question_Id="+question_Id+" and tiku_Id="+tiku_Id;
		  System.out.println(sql);
		  return jdbcTemplate.query(sql, new QuestionMapper());
	  }
	public List<Question1> quarybytikuidquestiontypelable(int tiku_Id,int question_type,int question_level){
		  String sql = "select * from ikaoshi.question where question_type="+question_type+" and question_level="+question_level+" and tiku_Id="+tiku_Id;
		  System.out.println(sql);
		  return jdbcTemplate.query(sql, new QuestionMapper());
	  }
	//下面是新版添加题库的相关的函数
	//获取教师发布的试卷的信息
	//create by lcq 2018年6月26日16:06:22
	public List<Question1> quarybytest_id(int test_Id){
		  String sql = "select * from ikaoshi.shijuanyangti where test_Id="+test_Id;
		  System.out.println(sql);
		  return jdbcTemplate.query(sql, new QuestionMapper());
	  }
	//删除试卷样题 
	//create by lcq 2018年6月26日16:30:54
	public boolean delateShijuanbytestid(int test_Id) {
		String sql = "delete from ikaoshi.shijuan where test_Id=?";
		return jdbcTemplate.update(sql,
		    new Object[] {test_Id}) == 1;
	}
	
	class QuestionMapper implements RowMapper<Question1> {
		 
		  @Override
		  public Question1 mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			Question1 q = new Question1();
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
