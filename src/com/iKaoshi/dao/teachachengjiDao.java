package com.iKaoshi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.bean.tea_cha_chengji;
import com.iKaoshi.dao.tikuDao.TikuxinxiMapper;

public class teachachengjiDao {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
		  }
	
	//根据考试号查看最高分
	//create by lcq 2018年6月20日11:10:25
	@SuppressWarnings("deprecation")
	public int getMaxscorebytestid(int test_Id) {
		String sql="select MAX(score) from ikaoshi.tea_cha_chengji where  test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	
	//根据考试号查看最低分
	//create by lcq 2018年6月20日11:10:47
	@SuppressWarnings("deprecation")
	public int getMinscorebytestid(int test_Id) {
		String sql="select min(score) from ikaoshi.tea_cha_chengji where state=2 and  test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	
	//根据考试号查看平均分
	//create by lcq 2018年6月20日11:10:53
	@SuppressWarnings("deprecation")
	public int getAvgscorebytestid(int test_Id) {
		String sql="select avg(score) from ikaoshi.tea_cha_chengji where  state=2 and test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	
	//根据考试号查看当前总人数
	//create by lcq 2018年6月20日11:10:55
	@SuppressWarnings("deprecation")
	public int getNNumbytestid(int test_Id) {
		String sql="select count(stu_Id) from ikaoshi.tea_cha_chengji where state=2 and test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	
	//根据考试号查看当前不及格人数
	//create by lcq 2018年6月20日11:10:58
	@SuppressWarnings("deprecation")
	public int getNBNumbytestid(int test_Id) {
		String sql="select count(stu_Id) from ikaoshi.tea_cha_chengji where  test_Id="+test_Id+" and state=2 and score<60;";
		return jdbcTemplate.queryForInt(sql);
	}
	
	//根据考试号查看当前的优秀人数
	//create by lcq 2018年6月20日11:11:00
	@SuppressWarnings("deprecation")
	public int getNYNumbybytestid(int test_Id) {
		String sql="select count(stu_Id) from ikaoshi.tea_cha_chengji where  test_Id="+test_Id+" and state=2 and score>=90;";
		return jdbcTemplate.queryForInt(sql);
	}
	
	//根据考试号查看缺考人数
	//create by lcq 2018年6月20日11:11:03
	@SuppressWarnings("deprecation")
	public int getNQNumby(int test_Id) {
		String sql="select count(stu_Id) from ikaoshi.tea_cha_chengji where  test_Id="+test_Id+" and state=0;";
		return jdbcTemplate.queryForInt(sql);
	}
	
	//根据考试号查看总人数
	//create by lcq 2018年6月20日11:17:49
	@SuppressWarnings("deprecation")
	public int getNumbytestid(int test_Id) {
		String sql="select count(stu_Id) from ikaoshi.tea_cha_chengji where  test_Id="+test_Id;
		return jdbcTemplate.queryForInt(sql);
	}
	
	//查看根据考试号查看成绩
	//create by lcq 2018年6月20日10:12:22
	public List<tea_cha_chengji> quaryBytestid(int test_id){
		  String sql = "select * from ikaoshi.tea_cha_chengji where test_Id="+test_id;
		  return jdbcTemplate.query(sql, new tea_cha_chengjiMapper());
	  }
	
	class tea_cha_chengjiMapper implements RowMapper<tea_cha_chengji> {
		 
		  @Override
		  public tea_cha_chengji mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			tea_cha_chengji t = new tea_cha_chengji();
		    t.setStu_Id(rs.getInt(1));
		    t.setStu_name(rs.getString(2));
		    t.setScore(rs.getInt(3));
		    t.setState(rs.getInt(4));
		    t.setTest_Id(rs.getInt(5));
		   // System.out.println(rs.getInt(1));
		   // System.out.println(rs.getInt(2));
		    return t;
		  }
		 
		  }
}
