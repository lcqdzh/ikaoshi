package com.iKaoshi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Shijuanzhuguan;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.dao.tikuDao.TikuxinxiMapper;

public class pigaiDao {

	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
		  }
	
	//根据考试的编号查看学生的主观题
	public List<Shijuanzhuguan> quaryBytestid(int test_id){
		  String sql = "SELECT t1.stu_Id,t1.test_Id,t1.tiku_Id,t1.question_Id,t2.question_content,t1.stu_answer,t1.score,t1.zgstate FROM ikaoshi.shijuan_zhuguan t1," + 
		  		"ikaoshi.question t2 where t1.question_Id=t2.question_Id and t1.tiku_Id=t2.tiku_Id and t2.question_type=3 and t1.test_Id="+test_id+";";
		  return jdbcTemplate.query(sql, new ShijuanzhuguanMapper());
	  }
	

	public boolean addTikuxinxi(Tikuxinxi t) {
		  String sql = "insert into ikaoshi.tea_tiku(tea_Id,tiku_name) values(?,?)";
		  //System.out.println(2);
		 return jdbcTemplate.update(sql,
		    new Object[] { t.getTea_Id(),t.getTiku_name()}) == 1;
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
		   // System.out.println(rs.getInt(1));
		   // System.out.println(rs.getInt(2));
		    return s;
		  }
		 
		  }
}
