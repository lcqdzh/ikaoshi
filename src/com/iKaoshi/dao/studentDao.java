package com.iKaoshi.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	//建立连接
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
	}
	/**
	 * 学生登陆查询
	 * @param id
	 * @param password
	 * @return
	 */
	public List<Student> login(int id,String password){
		  String sql = "select * from student where stu_Id='"+id+"'  and password='"+password+"'";
		  return jdbcTemplate.query(sql, new StudentMapper());
	}
	/**
	 * 返回学生待考试信息列表，包括test_Id,test_name,tea_name
	 * @param stu_Id
	 * @return
	 */
	public List<test> stu_test_list(int stu_Id,Timestamp time_now) {
		String sql="select test_Id,test_name,tea_name,time_long,begin_Time,end_Time from tea_test_info,teacher where '"+time_now+"'>begin_Time and '"+time_now+"'<end_Time and tea_test_info.tea_Id=teacher.tea_Id and test_Id in (select test_Id from stu_test_info where stu_Id="+stu_Id+" and state='0') order by test_Id asc";
		return jdbcTemplate.query(sql, new testMapper());
	}
	/**
	 * 返回学生算完分数的信息列表，包括test_Id,test_name,tea_name
	 * @param stu_Id
	 * @return
	 */
	public List<test> stu_score_done_list(int stu_Id,Timestamp time_now) {
		String sql="select tea_test_info.test_Id,test_name,tea_name,time_long,score,begin_Time,end_Time from tea_test_info,teacher,stu_test_info where tea_test_info.tea_Id=teacher.tea_Id and tea_test_info.test_Id=stu_test_info.test_Id and stu_test_info.test_Id in (select test_Id from stu_test_info where stu_Id="+stu_Id+" and state='2') and stu_Id="+stu_Id+" order by test_Id asc";
		return jdbcTemplate.query(sql, new testdMapper());
	}
	/**
	 * 返回学生还没算完分数的信息列表，包括test_Id,test_name,tea_name
	 * @param stu_Id
	 * @return
	 */
	public List<test> stu_testd_list(int stu_Id,Timestamp time_now) {
		String sql="select test_Id,test_name,tea_name,time_long,begin_Time,end_Time from tea_test_info,teacher where tea_test_info.tea_Id=teacher.tea_Id and test_Id in (select test_Id from stu_test_info where stu_Id="+stu_Id+" and state='1') order by test_Id asc";
		return jdbcTemplate.query(sql, new testMapper());
	}
	/**
	 * 返回还未开始的的考试列表，包括test_Id,test_name,tea_name
	 * @param stu_Id
	 * @return
	 */
	public List<test> not_begin_list(int stu_Id,Timestamp time_now) {
		String sql="select test_Id,test_name,tea_name,time_long,begin_Time,end_Time from tea_test_info,teacher where '"+time_now+"'<begin_Time and tea_test_info.tea_Id=teacher.tea_Id and test_Id in (select test_Id from stu_test_info where stu_Id="+stu_Id+" and state='0') order by test_Id asc";
		return jdbcTemplate.query(sql, new testMapper());
	}
	
	/**
	 * 返回学生过期未参加的信息列表，包括test_Id,test_name,tea_name
	 * @param stu_Id
	 * @return
	 */
	public List<test> overdue_list(int stu_Id,Timestamp time_now) {
		String sql="select test_Id,test_name,tea_name,time_long,begin_Time,end_Time from tea_test_info,teacher where  '"+time_now+"'>end_Time and tea_test_info.tea_Id=teacher.tea_Id and test_Id in (select test_Id from stu_test_info where stu_Id="+stu_Id+" and state='0') order by test_Id asc";
		return jdbcTemplate.query(sql, new testMapper());
	}
	
	
	
	/**
	 * 返回试卷中的单选题
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public List<question> get_question_dx(int stu_Id,int test_Id){
		String sql="select question_content,question_type,choice_A,choice_B,choice_C,choice_D,answer,dx_score,question.question_Id from shijuan_keguan,question,tea_test_info where question_type='1' and stu_Id="+stu_Id+"  and tea_test_info.test_Id="+test_Id+" and tea_test_info.test_Id=shijuan_keguan.test_Id and shijuan_keguan.question_Id=question.question_Id and shijuan_keguan.tiku_Id=question.tiku_Id";
		return jdbcTemplate.query(sql, new questionMapper());
	}
	/**
	 * 返回试卷中的判断题
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public List<question> get_question_pd(int stu_Id,int test_Id){
		String sql="select question_content,question_type,choice_A,choice_B,choice_C,choice_D,answer,pd_score,question.question_Id from shijuan_keguan,question,tea_test_info where question_type='2' and stu_Id="+stu_Id+"  and tea_test_info.test_Id="+test_Id+" and tea_test_info.test_Id=shijuan_keguan.test_Id and shijuan_keguan.question_Id=question.question_Id and shijuan_keguan.tiku_Id=question.tiku_Id";
		return jdbcTemplate.query(sql, new questionMapper());
	}
	/**
	 * 返回试卷中的简答题
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public List<question> get_question_dt(int stu_Id,int test_Id){	
		String sql="select question_content,question_type,choice_A,choice_B,choice_C,choice_D,answer,dt_score,question.question_Id from shijuan_zhuguan,question,tea_test_info where question_type='3' and stu_Id="+stu_Id+"  and tea_test_info.test_Id="+test_Id+" and tea_test_info.test_Id=shijuan_zhuguan.test_Id and shijuan_zhuguan.question_Id=question.question_Id and shijuan_zhuguan.tiku_Id=question.tiku_Id";
		return jdbcTemplate.query(sql, new questionMapper());
	}
	/**
	 * 获取试卷中简单单选的个数
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_count_dx_easy(int test_Id){
		String sql="select dx_easy from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 获取试卷中中等单选的个数
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_count_dx_medium(int test_Id){
		String sql="select dx_medium from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 获取试卷中hard单选的个数
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_count_dx_hard(int test_Id){
		String sql="select dx_hard from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}/**
	 * 获取试卷中简单判断的个数
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_count_pd_easy(int test_Id){
		String sql="select pd_easy from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 获取试卷中中等判断的个数
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_count_pd_medium(int test_Id){
		String sql="select pd_medium from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 获取试卷中hard判断的个数
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_count_pd_hard(int test_Id){
		String sql="select pd_hard from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 获取试卷中简单大题的个数
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_count_dt_easy(int test_Id){
		String sql="select dt_easy from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 获取试卷中中等大题的个数
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_count_dt_medium(int test_Id){
		String sql="select dt_medium from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 获取试卷中hard大题的个数
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_count_dt_hard(int test_Id){
		String sql="select dt_hard from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 存储学生的客观题答案
	 * @param stu_Id
	 * @param test_Id
	 * @param question_Id
	 * @param stu_answer
	 */
	public void set_keguan_answer(int stu_Id,int test_Id,int question_Id,int stu_answer){
		String sql="update shijuan_keguan set stu_answer="+stu_answer+" where stu_Id="+stu_Id+" and test_Id="+test_Id+" and question_Id="+question_Id;
		jdbcTemplate.update(sql);
	}
	/**
	 * 存储学生的主观题答案
	 * @param stu_Id
	 * @param test_Id
	 * @param question_Id
	 * @param stu_answer
	 */
	public void set_zhuguan_answer(int stu_Id,int test_Id,int question_Id,String stu_answer){
		String sql="update shijuan_zhuguan set stu_answer="+"'"+stu_answer+"'"+" where stu_Id="+stu_Id+" and test_Id="+test_Id+" and question_Id="+question_Id;
		jdbcTemplate.update(sql);
	}
	/**
	 * 获取客观题标准答案
	 * @param test_Id
	 * @param question_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_keguan_answer(int test_Id,int question_Id){
		String sql="select answer from question,tea_test_info where tea_test_info.tiku_Id=question.tiku_Id and test_Id="+test_Id+" and question_Id="+question_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 根据题目类型获取选择题或者判断题的分数
	 * @param test_Id
	 * @param question_type
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_score_keguan(int test_Id,int question_type){
		String sql;
		if(question_type==1){
			sql="select dx_score from tea_test_info where test_Id="+test_Id;
		}else{//指的是question_type=2
			sql="select pd_score from tea_test_info where test_Id="+test_Id;
		}
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 客观题分数加到总分上
	 * @author perfect
	 * @param stu_Id
	 * @param test_Id
	 * @param score
	 */
	public void add_score_keguan(int stu_Id,int test_Id,int score){
		String sql="update stu_test_info set score=score+"+score+" where stu_Id="+stu_Id+" and test_Id="+test_Id;
		jdbcTemplate.update(sql);
	}
	/**
	 * 更新学生试卷的状态
	 * @author perfect
	 * @param stu_Id
	 * @param test_Id
	 * @param state
	 */
	public void set_stu_test_state(int stu_Id,int test_Id,int state){
		String sql="update stu_test_info set state="+state+" where stu_Id="+stu_Id+" and test_Id="+test_Id;
		jdbcTemplate.update(sql);
	}
	/**
	 * 获取学生试卷成绩
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_stu_score(int stu_Id,int test_Id) {
		String sql="select score from stu_test_info where stu_Id="+stu_Id+" and test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 获取考试时间
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_test_time(int test_Id){
		String sql="select time_Long from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 返回用户名
	 * @param stu_Id
	 * @return
	 */
	public String get_stu_name(int stu_Id){
		String sql="select name from student where stu_Id="+stu_Id;
		return (String)jdbcTemplate.queryForObject(sql,java.lang.String.class);
	}
	/**
	 * 主要用于判断是否有对应的考试号
	 * @param test_Id
	 * @return test_name
	 */
	public List<test> get_test_name(int test_Id){
		String sql="select test_name from tea_test_info where test_Id="+test_Id;
		return jdbcTemplate.query(sql,new test_name_Mapper());
	}
	
	/**
	 * 获取学生的test_Id列表
	 * @param stu_Id
	 * @return
	 */
	public List<Integer> get_test_Id_list(int stu_Id){
		String sql="select test_Id from stu_test_info where state=2 and stu_Id="+stu_Id+" order by test_Id asc";
		return jdbcTemplate.query(sql, new test_Id_Mapper());
	}
	/**
	 * 根据获得test_name
	 * @param test_Id
	 * @return
	 */
	public String get_test_name_by_test_Id(int test_Id){
		String sql="select test_name from tea_test_info where test_Id="+test_Id;
		return (String)jdbcTemplate.queryForObject(sql,java.lang.String.class);
	}
	/**
	 * 获得某门考试的平均分
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_average_score(int test_Id){
		String sql="select AVG(score) from stu_test_info where state=2 and test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 获取在某名学生成绩之前的人数
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_num_before(int stu_Id,int test_Id){
		String sql="select count(*) from stu_test_info where state=2 and test_Id="+test_Id+" and score>=(select score from stu_test_info where stu_Id="+stu_Id+" and test_Id="+test_Id+")";
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 获取参加某门考试的总人数
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_num_all(int test_Id){
		String sql="select count(*) from stu_test_info where test_Id="+test_Id+" and state=2";
		return jdbcTemplate.queryForInt(sql);
	}
	/**
	 * 返回学生做过的单选题
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public List<question> get_dx_list(int stu_Id,int test_Id){
		String sql="select question_content,choice_A,choice_B,choice_C,choice_D,answer,stu_answer,dx_score from shijuan_keguan,question,tea_test_info where question_type='1' and stu_Id="+stu_Id+"  and tea_test_info.test_Id="+test_Id+" and tea_test_info.test_Id=shijuan_keguan.test_Id and shijuan_keguan.question_Id=question.question_Id and shijuan_keguan.tiku_Id=question.tiku_Id";
		return jdbcTemplate.query(sql, new question_Mapper());
	}
	/**
	 * 返回学生做过的判断题
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public List<question> get_pd_list(int stu_Id,int test_Id){
		String sql="select question_content,choice_A,choice_B,choice_C,choice_D,answer,stu_answer,pd_score from shijuan_keguan,question,tea_test_info where question_type='2' and stu_Id="+stu_Id+"  and tea_test_info.test_Id="+test_Id+" and tea_test_info.test_Id=shijuan_keguan.test_Id and shijuan_keguan.question_Id=question.question_Id and shijuan_keguan.tiku_Id=question.tiku_Id";
		return jdbcTemplate.query(sql, new question_Mapper());
	}
	/**
	 * 返回学生做过的简答题
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public List<question_dt> get_dt_list(int stu_Id,int test_Id){
		String sql="select question_content,choice_A,choice_B,choice_C,choice_D,answer,stu_answer,dt_score,score from shijuan_zhuguan,question,tea_test_info where question_type='3' and stu_Id="+stu_Id+"  and tea_test_info.test_Id="+test_Id+" and tea_test_info.test_Id=shijuan_zhuguan.test_Id and shijuan_zhuguan.question_Id=question.question_Id and shijuan_zhuguan.tiku_Id=question.tiku_Id";
		return jdbcTemplate.query(sql, new question_dt_Mapper());
	}
	/**
	 * 返回学生密码
	 * @param stu_Id
	 * @return
	 */
	public String get_stu_password(int stu_Id){
		String sql="select password from student where stu_Id="+stu_Id;
		return (String)jdbcTemplate.queryForObject(sql,java.lang.String.class);
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
	 * 获取学生考试状态
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int get_test_state(int stu_Id,int test_Id){
		String sql="select state from stu_test_info where test_Id="+test_Id+" and stu_Id="+stu_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	
	/**
	 * 学生考试信息查询
	 * @param id
	 * @param password
	 * @return
	 */
	public List<stu_test> stu_test(int stu_Id,int test_Id){
		  String sql = "select * from stu_test_info where stu_Id='"+stu_Id+"'  and test_Id='"+test_Id+"'";
		  return jdbcTemplate.query(sql, new stu_test_Mapper());
	}
	/**
	 * 插入学生考试信息
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean add_stu_test(int stu_Id,int test_Id){
		  String sql = "insert into stu_test_info(stu_Id,test_Id) values("+stu_Id+","+test_Id+")";
		  return jdbcTemplate.update(sql)==1;
	}
	/**
	 * 查看consult表有没有对应的申诉信息
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public List<stu_test> get_consult_list(int stu_Id,int test_Id){
		String sql="select * from consult where stu_Id="+stu_Id+" and test_Id="+test_Id;
		return jdbcTemplate.query(sql, new stu_test_Mapper());
	}
	/**
	 * 插入申诉信息
	 * @param stu_Id
	 * @param test_Id
	 * @param question
	 * @return
	 */
	public boolean insert_consult(int stu_Id,int test_Id,String question){
		String sql="insert into consult(stu_Id,test_Id,question,state) values("+stu_Id+","+test_Id+",'"+question+"',0)";
		return (jdbcTemplate.update(sql)==1);
	}
	/**
	 * 查看学生申诉信息
	 * @param stu_Id
	 * @return
	 */
	public List<consult> get_consult_list(int stu_Id){
		String sql="select consult.test_Id,test_name,question,answer from consult,tea_test_info where consult.test_Id=tea_test_info.test_Id and stu_Id="+stu_Id;
		return jdbcTemplate.query(sql, new consultMapper());
	}
	/**
	 * 插入客观题信息
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public boolean insert_keguan(int stu_Id,int test_Id){
		String sql="insert into shijuan_keguan(stu_Id,test_Id,tiku_Id,question_Id) select stu_Id,test_Id,shijuan.tiku_Id,shijuan.question_Id from student,shijuan,question where stu_Id="+stu_Id+" and test_Id="+test_Id+" and question.question_Id=shijuan.question_Id and question.tiku_Id=shijuan.tiku_Id and question_type<3";
		return jdbcTemplate.update(sql)>0;
	}
	/**
	 * 插入主观题信息
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public boolean insert_zhuguan(int stu_Id,int test_Id){
		String sql="insert into shijuan_zhuguan(stu_Id,test_Id,tiku_Id,question_Id) select stu_Id,test_Id,shijuan.tiku_Id,shijuan.question_Id from student,shijuan,question where stu_Id="+stu_Id+" and test_Id="+test_Id+" and question.question_Id=shijuan.question_Id and question.tiku_Id=shijuan.tiku_Id and question_type=3";
		return jdbcTemplate.update(sql)>0;
	}
	

	/**
	 * 学生客观试题信息查询
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public List<stu_test> stu_test_keguan(int stu_Id,int test_Id){
		  String sql = "select stu_Id,test_Id from shijuan_keguan where stu_Id='"+stu_Id+"'  and test_Id='"+test_Id+"'";
		  return jdbcTemplate.query(sql, new stu_test_Mapper());
	}
	/**
	 * 学生主观试题信息查询
	 * @param stu_Id
	 * @param test_Id
	 * @return
	 */
	public List<stu_test> stu_test_zhuguan(int stu_Id,int test_Id){
		  String sql = "select stu_Id,test_Id from shijuan_zhuguan where stu_Id='"+stu_Id+"'  and test_Id='"+test_Id+"'";
		  return jdbcTemplate.query(sql, new stu_test_Mapper());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * consult的映射
	 * @author perfect
	 *
	 */
	class consultMapper implements RowMapper<consult>{
		@Override
		public consult mapRow(ResultSet rs, int rowNum) throws SQLException {
			consult consult=new consult();
			consult.setTest_Id(rs.getInt(1));
			consult.setTest_name(rs.getString(2));
			consult.setQuestion(rs.getString(3));
			consult.setAnswer(rs.getString(4));
			
			return consult;
		}
	}
	
	
	/**
	 * stu_test的映射
	 * @author perfect
	 *
	 */
	class stu_test_Mapper implements RowMapper<stu_test>{
		@Override
		public stu_test mapRow(ResultSet rs, int rowNum) throws SQLException {
			stu_test stu_test=new stu_test();
			stu_test.setStu_Id(rs.getInt(1));
			stu_test.setTest_Id(rs.getInt(2));
			return stu_test;
		}
	}
	

	/**
	 * int到list的映射
	 * @author perfect
	 *
	 */
	class test_Id_Mapper implements RowMapper<Integer>{
		@Override
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			return rs.getInt(1);
		}
	}
	/**
	 * 获取test_name，专为判断考试号是否存在而产生
	 * @author perfect
	 *
	 */
	class test_name_Mapper implements RowMapper<test>{
		@Override
		public test mapRow(ResultSet rs, int rowNum) throws SQLException {
			test test=new test();
			test.setTest_name(rs.getString(1));
			return test;
		}
	}
	/**
	 * 将学生待考试信息查询结果装入test的List中
	 * @author perfect
	 *
	 */
	class testMapper implements RowMapper<test> {
		 
		  @Override
		  public test mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			test test=new test();
			test.setTest_Id(rs.getInt(1));
			test.setTest_name(rs.getString(2));
			test.setTea_name(rs.getString(3));
			test.setTime_long(rs.getInt(4));
			test.setBegin_Time(rs.getTimestamp(5));
			test.setEnd_Time(rs.getTimestamp(6));
			return test;
		  }
		 
	}
	/**
	 * 将学生已经考完并算完分数的考试信息查询结果装入test的List中
	 * @author perfect
	 *
	 */
	class testdMapper implements RowMapper<test> {
		 
		  @Override
		  public test mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			test test=new test();
			test.setTest_Id(rs.getInt(1));
			test.setTest_name(rs.getString(2));
			test.setTea_name(rs.getString(3));
			test.setTime_long(rs.getInt(4));
			test.setTest_score(rs.getInt(5));
			test.setBegin_Time(rs.getTimestamp(6));
			test.setEnd_Time(rs.getTimestamp(7));
			return test;
		  }
		 
	}
	
		
	/**
	 * 将试题查询结果装入question的List中
	 * @author perfect
	 *
	 */
	class questionMapper implements RowMapper<question> {
		 
		  @Override
		  public question mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			question question=new question();
			
			question.setQuestion_content(rs.getString(1));
			question.setQuestion_type(rs.getInt(2));
			question.setChoice_A(rs.getString(3));
			question.setChoice_B(rs.getString(4));
			question.setChoice_C(rs.getString(5));
			question.setChoice_D(rs.getString(6));
			question.setAnswer(rs.getInt(7));
			question.setQuestion_score(rs.getInt(8));
			question.setQuestion_Id(rs.getInt(9));
			return question;
		  }
		 
	}
	/**
	 * 将试题查询结果装入question的List中，用于选择和判断
	 * @author perfect
	 *
	 */
	class question_Mapper implements RowMapper<question> {
		 
		  @Override
		  public question mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			question question=new question();
			
			question.setQuestion_content(rs.getString(1));
			question.setChoice_A(rs.getString(2));
			question.setChoice_B(rs.getString(3));
			question.setChoice_C(rs.getString(4));
			question.setChoice_D(rs.getString(5));
			question.setAnswer(rs.getInt(6));
			question.setStu_answer(rs.getInt(7));
			question.setQuestion_score(rs.getInt(8));			
			return question;
		  }
		 
	}
	/**
	 * 将试题查询结果装入question的List中，用于大题
	 * @author perfect
	 *
	 */
	class question_dt_Mapper implements RowMapper<question_dt> {
		 
		  @Override
		  public question_dt mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			question_dt question=new question_dt();
			
			question.setQuestion_content(rs.getString(1));
			question.setChoice_A(rs.getString(2));
			question.setChoice_B(rs.getString(3));
			question.setChoice_C(rs.getString(4));
			question.setChoice_D(rs.getString(5));
			question.setAnswer(rs.getInt(6));
			question.setStu_answer(rs.getString(7));
			question.setQuestion_score(rs.getInt(8));		
			question.setScore(rs.getInt(9));
			return question;
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
		   // System.out.println(rs.getInt(1));
		   // System.out.println(rs.getInt(2));
		    return student;
		  }
		 
	}
}
