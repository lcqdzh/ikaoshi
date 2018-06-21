package com.iKaoshi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.iKaoshi.bean.Question1;
import com.iKaoshi.bean.TeaTestInfo;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.dao.tikuDao.TikuxinxiMapper;

public class teatestinfoDao {
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
		  }
	
	//获取当前数据库中最大的考试编号
	//create by lcq 2018年6月17日15:30:26
	@SuppressWarnings("deprecation")
	public int getMaxtestid()
	{
		return jdbcTemplate.queryForInt("SELECT MAX(test_Id) FROM ikaoshi.tea_test_info ;");
	}
	//根据老师id与日期 查看已经结束的考试
	//create by lcq 2018年6月19日10:45:17
	public List<TeaTestInfo> quaryOverbyteaiddate(int tea_id, Timestamp today){
		  String sql = "select * from ikaoshi.tea_test_info where tea_Id="+tea_id+" and end_Time<'"+today+"';";
		  return jdbcTemplate.query(sql, new teatestinfoMapper());
	  }
	//根据老师id与日期 查看未结束的考试
	//create by lcq 2018年6月19日10:45:17
	public List<TeaTestInfo> quaryNOverbyteaiddate(int tea_id, Timestamp today){
		  String sql = "select * from ikaoshi.tea_test_info where tea_Id="+tea_id+" and end_Time>'"+today+"';";
		  return jdbcTemplate.query(sql, new teatestinfoMapper());
	  }
	
	//查看老师发布的考试
	//create by lcq 2018年6月18日17:00:51
	public List<TeaTestInfo> quarybyteaid(int tea_id){
		  String sql = "select * from ikaoshi.tea_test_info where tea_Id="+tea_id;
		  return jdbcTemplate.query(sql, new teatestinfoMapper());
	  }
	//根据考试编号查看考试信息
	//create by lcq 2018年6月18日20:09:46
	public List<TeaTestInfo> quarybytestid(int test_id){
		  String sql = "select * from ikaoshi.tea_test_info where test_Id="+test_id;
		  return jdbcTemplate.query(sql, new teatestinfoMapper());
	  }
	//修改根据考试号修改老师发布的考试
	//create by lcq 2018年6月18日20:49:51
	public boolean updateteatestinfobyone(TeaTestInfo t) {
		  String sql = "update ikaoshi.tea_test_info set test_name=?,tiku_Id=?,tea_Id=?,begin_Time=?," + 
		  		"end_Time=?,time_Long=?,dx_easy=?,dx_medium=?,dx_hard=?,pd_easy=?,pd_medium=?,pd_hard=?,dt_easy=?,"
		  		+ "dt_medium=?,dt_hard=?,dx_score=?,pd_score=?,dt_score=? where test_Id=?;";
		System.out.println("sql"+t.toString());
		 return jdbcTemplate.update(sql,new Object[] {t.getTest_name(),t.getTiku_id(),t.getTea_id(),t.getBegin_time(),
				 t.getEnd_time(),t.getTime_long(),t.getDx_easy(),t.getDx_medium(),t.getDx_hard(),t.getPd_easy(),t.getPd_medium(),t.getPd_hard(),t.getDt_easy()
				 ,t.getDt_medium(),t.getDt_hard(),t.getDx_score(),t.getPd_score(),t.getDt_score(),t.getTest_id()}) == 1;
		  }
	
	//插入一条考试信息
	//create by lcq 2018年6月18日15:56:08
	public boolean addTeatestinfo(TeaTestInfo t) {
		  String sql = "insert into ikaoshi.tea_test_info(test_name,test_Id,tiku_Id,tea_Id,begin_Time,"
		  		+ "end_Time,time_Long,dx_easy,dx_medium,dx_hard,pd_easy,pd_medium,pd_hard,dt_easy,dt_medium,dt_hard,dx_score,pd_score,dt_score) "
		  		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		  //System.out.println(2);
		 return jdbcTemplate.update(sql,
		    new Object[] { t.getTest_name(),t.getTest_id(),t.getTiku_id(),t.getTea_id(),t.getBegin_time(),t.getEnd_time(),t.getTime_long(),t.getDx_easy(),t.getDx_medium()
		    		,t.getDx_hard(),t.getPd_easy(),t.getPd_medium(),t.getPd_hard(),t.getDt_easy(),t.getDt_medium(),t.getDt_hard(),t.getDx_score(),t.getPd_score(),t.getDt_score()}) == 1;
		  }
	
	//映射关系
	//create by lcq 2018年6月18日15:55:43
	class teatestinfoMapper implements RowMapper<TeaTestInfo> {
		 
		  @Override
		  public TeaTestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		    // TODO Auto-generated method stub
			  TeaTestInfo t = new TeaTestInfo();
		    t.setTest_name(rs.getString(1));
		    t.setTest_id(rs.getInt(2));
		    t.setTiku_id(rs.getInt(3));
		    t.setTea_id(rs.getInt(4));
		    t.setBegin_time(rs.getTimestamp(5));
		    t.setEnd_time(rs.getTimestamp(6));
		    t.setTime_long(rs.getInt(7));
		    t.setDx_easy(rs.getInt(8));
		    t.setDx_medium(rs.getInt(9));
		    t.setDx_hard(rs.getInt(10));
		    t.setPd_easy(rs.getInt(11));
		    t.setPd_medium(rs.getInt(12));
		    t.setPd_hard(rs.getInt(13));
		    t.setDt_easy(rs.getInt(14));
		    t.setDt_medium(rs.getInt(15));
		    t.setDt_hard(rs.getInt(16));
		    t.setDx_score(rs.getInt(17));
		    t.setPd_score(rs.getInt(18));
		    t.setDt_score(rs.getInt(19));
		    return t;
		  }
		 
		  }
}
