package com.iKaoshi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Teaconsult;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.dao.tikuDao.TikuxinxiMapper;

public class teaconsultDao {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
		  }
	
	//查看所有回复信息
	//create by lcq 2018年6月21日21:28:31
	public List<Teaconsult> quaryAll(int tea_id){
		  String sql = "select * from ikaoshi.teaconsult where tea_Id="+tea_id;
		  return jdbcTemplate.query(sql, new TeaconsultMapper());
	  }
	//查看完成回复信息
	//create by lcq 2018年6月21日21:28:31
	public List<Teaconsult> quaryOver(int tea_id){
		  String sql = "select * from ikaoshi.teaconsult where state=1 and tea_Id="+tea_id;
		  return jdbcTemplate.query(sql, new TeaconsultMapper());
	  }
	
	//查看未完成回复信息
	//create by lcq 2018年6月21日21:28:31
	public List<Teaconsult> quaryWait(int tea_id){
		  String sql = "select * from ikaoshi.teaconsult where state=0 and tea_Id="+tea_id;
		  return jdbcTemplate.query(sql, new TeaconsultMapper());
	  }
	
	//更新教师回复
	//create by lcq 2018年6月21日21:39:34
	public boolean updateConsult(int stu_id,int test_id,String answer) {
		  String sql = "update ikaoshi.consult set answer='"+answer+"',state=1 where test_Id="+test_id+" and stu_Id="+stu_id+";";
		  //System.out.println(2);
		 return jdbcTemplate.update(sql) == 1;
		  }
	
	
	
	
	
	
	class TeaconsultMapper implements RowMapper<Teaconsult> {
		 
		  @Override
		  public Teaconsult mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			  Teaconsult t = new Teaconsult();
		    t.setStu_Id(rs.getInt(1));
		    t.setStu_name(rs.getString(2));
		    t.setTest_Id(rs.getInt(3));
		    t.setQuestion(rs.getString(4));
		    t.setAnswer(rs.getString(5));
		    t.setState(rs.getInt(6));
		    System.out.println("state="+t.getState());
		   // System.out.println(rs.getInt(1));
		   // System.out.println(rs.getInt(2));
		    return t;
		  }
		 
		  }
}
