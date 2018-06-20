package com.iKaoshi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Question1;
import com.iKaoshi.bean.Shijuanzhuguan;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.dao.tikuDao.TikuxinxiMapper;

public class pigaiDao {

	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
		  }
	
	//根据考试的编号查看学生的主观题
	//create by lcq 2018年6月19日20:19:29
	public List<Shijuanzhuguan> quaryBytestid(int test_id){
		  String sql = "SELECT t1.stu_Id,t1.test_Id,t1.tiku_Id,t1.question_Id,t2.question_content,t1.stu_answer,t1.score,t1.zgstate,t3.dt_score FROM ikaoshi.shijuan_zhuguan t1," + 
		  		"ikaoshi.question t2,ikaoshi.tea_test_info t3 where t3.test_Id=t1.test_Id and t1.question_Id=t2.question_Id and t1.tiku_Id=t2.tiku_Id and t2.question_type=3 and t1.test_Id="+test_id+";";
		  return jdbcTemplate.query(sql, new ShijuanzhuguanMapper());
	  }
	
	//根据学号 考试号 试题号获取学生答题   //应该是返回的是一个对象
	//create by lcq 2018年6月19日20:20:40
	public List<Shijuanzhuguan> getBytestidstuidquestionidtikuid(int test_id,int stu_id,int question_id,int tiku_id){
		 String sql = "SELECT t1.stu_Id,t1.test_Id,t1.tiku_Id,t1.question_Id,t2.question_content,t1.stu_answer,t1.score,t1.zgstate,t3.dt_score FROM ikaoshi.shijuan_zhuguan t1," + 
			  		"ikaoshi.question t2,ikaoshi.tea_test_info t3 where t3.test_Id=t1.test_Id and t1.question_Id=t2.question_Id and t1.tiku_Id=t2.tiku_Id and t2.question_type=3 and t1.test_Id="+test_id+" and t1.stu_Id="+stu_id+" and t1.question_Id="+question_id+" and t1.tiku_id="+tiku_id+";";
		  return jdbcTemplate.query(sql, new ShijuanzhuguanMapper());
	  }
	
	//根据学号 考试号 试题号获取学生答题 //返回的是一个list 应该为按照小于传入的试题号 后倒序排列 即为最大的是此试题号之前的试题信息
	//create by lcq 2018年6月19日23:07:46
	public List<Shijuanzhuguan> quaryBytestidstuidquestionidtikuid_xiangtongxuehaos(int test_id,int stu_id,int question_id,int tiku_id){
		 String sql = "SELECT t1.stu_Id,t1.test_Id,t1.tiku_Id,t1.question_Id,t2.question_content,t1.stu_answer,t1.score,t1.zgstate,t3.dt_score FROM ikaoshi.shijuan_zhuguan t1," + 
			  		"ikaoshi.question t2,ikaoshi.tea_test_info t3 where t3.test_Id=t1.test_Id and t1.question_Id=t2.question_Id and t1.tiku_Id=t2.tiku_Id and t2.question_type=3 and t1.test_Id="+test_id+" and t1.stu_Id="+stu_id+" and t1.question_Id<"+question_id+" and t1.tiku_id="+tiku_id+" ORDER BY t1.question_Id DESC;";
		 System.out.println("xiangtxuehaos="+sql); 
		 return jdbcTemplate.query(sql, new ShijuanzhuguanMapper());
	  }
	
	//根据学号 考试号 获取学生答题 //返回的是一个list 应该为小于学号 倒序排列 得到的第一个为学号最大 试题号最大
	//create by lcq 2018年6月19日23:14:02
	public List<Shijuanzhuguan> quaryBytestidstuidtikuid_butongxuehaos(int test_id,int stu_id,int tiku_id){
		 String sql = "SELECT t1.stu_Id,t1.test_Id,t1.tiku_Id,t1.question_Id,t2.question_content,t1.stu_answer,t1.score,t1.zgstate,t3.dt_score FROM ikaoshi.shijuan_zhuguan t1," + 
			  		"ikaoshi.question t2,ikaoshi.tea_test_info t3 where t3.test_Id=t1.test_Id and t1.question_Id=t2.question_Id and t1.tiku_Id=t2.tiku_Id and t2.question_type=3 and t1.test_Id="+test_id+" and t1.stu_Id<"+stu_id+" and  t1.tiku_id="+tiku_id+" ORDER BY t1.stu_Id DESC,t1.question_Id DESC ;";
		 System.out.println("butxuehaos="+sql);  
		 return jdbcTemplate.query(sql, new ShijuanzhuguanMapper());
	  }
	//根据学号 考试号 试题号获取学生答题 //返回的是一个list 应该为按照大于传入的试题号 后顺序排列 即为最大的是此试题号之后的试题信息
	//create by lcq 2018年6月19日23:07:46
	public List<Shijuanzhuguan> quaryBytestidstuidquestionidtikuid_xiangtongxuehaob(int test_id,int stu_id,int question_id,int tiku_id){
		 String sql = "SELECT t1.stu_Id,t1.test_Id,t1.tiku_Id,t1.question_Id,t2.question_content,t1.stu_answer,t1.score,t1.zgstate,t3.dt_score FROM ikaoshi.shijuan_zhuguan t1," + 
			  		"ikaoshi.question t2,ikaoshi.tea_test_info t3 where t3.test_Id=t1.test_Id and t1.question_Id=t2.question_Id and t1.tiku_Id=t2.tiku_Id and t2.question_type=3 and t1.test_Id="+test_id+" and t1.stu_Id="+stu_id+" and t1.question_Id>"+question_id+" and t1.tiku_id="+tiku_id+" ORDER BY t1.question_Id ASC;";
		  return jdbcTemplate.query(sql, new ShijuanzhuguanMapper());
	  }
	
	//根据学号 考试号 获取学生答题 //返回的是一个list 应该为大于学号 顺序排列 得到的第一个为学号最小 试题号最小
	//create by lcq 2018年6月19日23:14:02
	public List<Shijuanzhuguan> quaryBytestidstuidtikuid_butongxuehaob(int test_id,int stu_id,int tiku_id){
		 String sql = "SELECT t1.stu_Id,t1.test_Id,t1.tiku_Id,t1.question_Id,t2.question_content,t1.stu_answer,t1.score,t1.zgstate,t3.dt_score FROM ikaoshi.shijuan_zhuguan t1," + 
			  		"ikaoshi.question t2,ikaoshi.tea_test_info t3 where t3.test_Id=t1.test_Id and t1.question_Id=t2.question_Id and t1.tiku_Id=t2.tiku_Id and t2.question_type=3 and t1.test_Id="+test_id+" and t1.stu_Id>"+stu_id+" and  t1.tiku_id="+tiku_id+" ORDER BY t1.stu_Id ASC,t1.question_Id ASC ;";
		  return jdbcTemplate.query(sql, new ShijuanzhuguanMapper());
	  }
	
	
	//更新大题
	//create by lcq 2018年6月19日21:44:48
	public boolean updatebysjzg(Shijuanzhuguan s) {
		  String sql = "update ikaoshi.shijuan_zhuguan set score=?,zgstate=1 where stu_Id=? and test_Id=? and tiku_Id=? and question_Id=?;";
		
		 return jdbcTemplate.update(sql,new Object[] {s.getScore(),s.getStu_id(),s.getTest_id(),s.getTiku_id(),s.getQuestion_id()}) == 1;
		  }
	//查看学生是否还有为批改的试题
	//create by lcq 2018年6月20日00:01:46
	public List<Shijuanzhuguan> getBytestidstuidtikuid_wpg(int test_id,int stu_id,int tiku_id){
		 String sql = "SELECT t1.stu_Id,t1.test_Id,t1.tiku_Id,t1.question_Id,t2.question_content,t1.stu_answer,t1.score,t1.zgstate,t3.dt_score FROM ikaoshi.shijuan_zhuguan t1," + 
			  		"ikaoshi.question t2,ikaoshi.tea_test_info t3 where t3.test_Id=t1.test_Id and t1.question_Id=t2.question_Id and t1.tiku_Id=t2.tiku_Id and t2.question_type=3 and t1.test_Id="+test_id+" and t1.stu_Id="+stu_id+" and  t1.tiku_id="+tiku_id+" and t1.zgstate=0;";
		  return jdbcTemplate.query(sql, new ShijuanzhuguanMapper());
	  }
	public List<Shijuanzhuguan> getBytestidstuidtikuid_pg(int test_id,int stu_id,int tiku_id){
		 String sql = "SELECT t1.stu_Id,t1.test_Id,t1.tiku_Id,t1.question_Id,t2.question_content,t1.stu_answer,t1.score,t1.zgstate,t3.dt_score FROM ikaoshi.shijuan_zhuguan t1," + 
			  		"ikaoshi.question t2,ikaoshi.tea_test_info t3 where t3.test_Id=t1.test_Id and t1.question_Id=t2.question_Id and t1.tiku_Id=t2.tiku_Id and t2.question_type=3 and t1.test_Id="+test_id+" and t1.stu_Id="+stu_id+" and  t1.tiku_id="+tiku_id+" and t1.zgstate=1;";
		  return jdbcTemplate.query(sql, new ShijuanzhuguanMapper());
	  }
	//更新学生考试状态
	//create by lcq 2018年6月20日00:12:20
	public boolean updateStutestinfo(int stu_id,int test_id,int score) {
		  String sql = "update ikaoshi.stu_test_info set score=score+?,state=2 where stu_Id=? and test_Id=?;";
		
		 return jdbcTemplate.update(sql,new Object[] {score,stu_id,test_id}) == 1;
		  }
	
	
	
	class ShijuanzhuguanMapper implements RowMapper<Shijuanzhuguan> {
		 
		  @Override
		  public Shijuanzhuguan mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			 Shijuanzhuguan s = new Shijuanzhuguan();
		    s.setStu_id(rs.getInt(1));
		    s.setTest_id(rs.getInt(2));
		    s.setTiku_id(rs.getInt(3));
		    s.setQuestion_id(rs.getInt(4));
		    s.setQuestion(rs.getString(5));
		    s.setStu_answer(rs.getString(6));
		    s.setScore(rs.getInt(7));
		    s.setZgstate(rs.getInt(8));
		    s.setDt_score(rs.getInt(9));
		   // System.out.println(rs.getInt(1));
		   // System.out.println(rs.getInt(2));
		    return s;
		  }
		 
		  }
}
