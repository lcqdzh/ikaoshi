package com.iKaoshi.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthStyle;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iKaoshi.dao.studentDao;

import com.iKaoshi.bean.*;
public class studentService {
	/**
	 * 学生登录信息判断
	 * @param id
	 * @param password
	 * @return
	 */
	 public static boolean login(int id,String password)
    {
        //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<Student> ss=null;
		ss=sdao.login(id, password);
		if(ss.size()==0)
		{
			return false;
		}else {
			return true;
		}
    }
	 
	 
		/**
		 * 学生考试信息判断
		 * @param id
		 * @param password
		 * @return
		 */
		 public static boolean stu_test_exist(int stu_Id,int test_Id)
	    {
	        //处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			studentDao sdao = (studentDao) context.getBean("studentDao");
			List<stu_test> ss=null;
			ss=sdao.stu_test(stu_Id, test_Id);
			if(ss.size()==0)
			{
				return false;
			}else {
				return true;
			}
	    }
	 /**
	  * 添加考试，查看考试号是否存在
	  * @param test_Id
	  * @return
	  */
	 public static boolean test_Id_exist(int test_Id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<test> test_name=sdao.get_test_name(test_Id);
		if(test_name.size()!=0){
			return true;
		}else {
			return false;
		}
	 }
	 /**
	  * 获取待考试试卷列表
	  * @param stu_Id
	  * @return
	  */
	 public static List<test> get_stu_test_list(int stu_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		List<test> test_list=null;
		test_list=sdao.stu_test_list(stu_Id,d);
		//System.out.println(test_list.size());
		return test_list;
	 }
	 /**
	  * 获取提交但是还没阅卷的考试试卷列表
	  * @param stu_Id
	  * @return
	  */
	 public static List<test> get_stu_testd_list(int stu_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<test> test_list=null;
		Timestamp d = new Timestamp(System.currentTimeMillis());
		test_list=sdao.stu_testd_list(stu_Id,d);
		return test_list;
	 }
	 /**
	  * 获取已经阅卷完成的试卷列表
	  * @param stu_Id
	  * @return
	  */
	 public static List<test> get_stu_score_done_list(int stu_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<test> test_list=null;
		Timestamp d = new Timestamp(System.currentTimeMillis());
		test_list=sdao.stu_score_done_list(stu_Id,d);
		return test_list;
	 }
	 /**
	  * 获取尚未开始的试卷列表
	  * @param stu_Id
	  * @return
	  */
	 public static List<test> get_not_begin_list(int stu_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<test> test_list=null;
		Timestamp d = new Timestamp(System.currentTimeMillis());
		test_list=sdao.not_begin_list(stu_Id,d);
		return test_list;
	 }
	 /**
	  * 获取已经过期的试卷列表
	  * @param stu_Id
	  * @return
	  */
	 public static List<test> get_overdue_list(int stu_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<test> test_list=null;
		Timestamp d = new Timestamp(System.currentTimeMillis());
		test_list=sdao.overdue_list(stu_Id,d);
		return test_list;
	 }
	 
	 
	 /**
	  * 获取试卷中的单选题目列表
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static List<question> get_question_dx(int stu_Id,int test_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<question> timu_list=null;
		timu_list=sdao.get_question_dx(stu_Id, test_Id);
		return timu_list;
	 }
	 /**
	  * 获取试卷中的判断题目列表
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static List<question> get_question_pd(int stu_Id,int test_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<question> timu_list=null;
		timu_list=sdao.get_question_pd(stu_Id, test_Id);
		return timu_list;
	 }
	 /**
	  * 获取试卷中的简答题目列表
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static List<question> get_question_dt(int stu_Id,int test_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<question> timu_list=null;
		timu_list=sdao.get_question_dt(stu_Id, test_Id);
		return timu_list;
	 }
	 /**
	  * 获取试卷中单选的个数
	  * @param test_Id
	  * @return
	  */
	 public static int get_count_dx(int test_Id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 int dx_easy=sdao.get_count_dx_easy(test_Id);
		 int dx_medium=sdao.get_count_dx_medium(test_Id);
		 int dx_hard=sdao.get_count_dx_hard(test_Id);
		 int dx_count=dx_easy+dx_hard+dx_medium;
		 return dx_count;
	 }
	 /**
	  * 获取试卷中判断的个数
	  * @param test_Id
	  * @return
	  */
	 public static int get_count_pd(int test_Id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 int pd_easy=sdao.get_count_pd_easy(test_Id);
		 int pd_medium=sdao.get_count_pd_medium(test_Id);
		 int pd_hard=sdao.get_count_pd_hard(test_Id);
		 int pd_count=pd_easy+pd_hard+pd_medium;
		 return pd_count;
	 }
	 /**
	  * 获取试卷中大题的个数
	  * @param test_Id
	  * @return
	  */
	 public static int get_count_dt(int test_Id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 int dt_easy=sdao.get_count_dt_easy(test_Id);
		 int dt_medium=sdao.get_count_dt_medium(test_Id);
		 int dt_hard=sdao.get_count_dt_hard(test_Id);
		 int dt_count=dt_easy+dt_hard+dt_medium;
		 return dt_count;
	 }
	 /**
	  * 存储客观题作答
	  * @param stu_Id
	  * @param test_Id
	  * @param question_Id
	  * @param stu_answer
	  */
	 public static void set_keguan_answer(int stu_Id,int test_Id,int question_Id,int stu_answer){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 sdao.set_keguan_answer(stu_Id, test_Id, question_Id, stu_answer);
	 }
	 /**
	  * 存储主观题作答
	  * @param stu_Id
	  * @param test_Id
	  * @param question_Id
	  * @param stu_answer
	  */
	 public static void set_zhuguan_answer(int stu_Id,int test_Id,int question_Id,String stu_answer){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 sdao.set_zhuguan_answer(stu_Id, test_Id, question_Id, stu_answer);
	 }
	 
	 /**
	  * 获取问题答案
	  * @param stu_Id
	  * @param test_Id
	  * @param question_Id
	  * @param stu_answer
	  */
	 public static int get_keguan_answer(int test_Id,int question_Id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 int answer=sdao.get_keguan_answer(test_Id, question_Id);
		 return  answer;
	 }
	 /**
	  * 将客观题分数加到总分数上
	  * @param stu_Id
	  * @param test_Id
	  * @param question_type
	  */
	 public static void cal_keguan_score(int stu_Id,int test_Id,int question_type){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 //根据test_Id获得题目分数，然后根据test_Id 和stu_Id将题目分数写进stu_test_info表中
		 int score=sdao.get_score_keguan(test_Id,question_type);
		 sdao.add_score_keguan(stu_Id,test_Id,score);
	 }
	 /**
	  * 更新学生考试状态
	  * @param stu_Id
	  * @param test_Id
	  * @param state
	  */
	 public static void set_stu_test_state(int stu_Id,int test_Id,int state){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 sdao.set_stu_test_state(stu_Id, test_Id, state);
	 }
	 public static int get_stu_score(int stu_Id,int test_Id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 int score=sdao.get_stu_score(stu_Id, test_Id);
		 return score;
	 }
	 /**
	  * 得到考试小时数
	  * @param test_Id
	  * @return
	  */
	 public static int get_test_hour(int test_Id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 int time_Long=sdao.get_test_time(test_Id);
		 int hour=time_Long/60;
		 return hour;
	 }
	 /**
	  * 得到考试分钟数
	  * @param test_Id
	  * @return
	  */
	 public static int get_test_minute(int test_Id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 int time_Long=sdao.get_test_time(test_Id);
		 int minute=time_Long%60;
		 return minute;
	 }
	 /**
	  * 根据学号得到学生名字
	  * @param stu_Id
	  * @return
	  */
	 public static String get_stu_name(int stu_Id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 String stu_name=sdao.get_stu_name(stu_Id);
		 return stu_name;
	 }
	 /**
	  * 根据学号获得已经考过的试卷成绩分析
	  * @param stu_Id
	  * @return
	  */
	 public static List<test_analyse> get_test_analyse_list(int stu_Id) {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 studentDao sdao = (studentDao) context.getBean("studentDao");
		 List<Integer> test_Id_list=sdao.get_test_Id_list(stu_Id);
		 List<test_analyse> test_analyse_list=new ArrayList<>();
		
		 for(int i=0;i<test_Id_list.size();i++){
			test_analyse test=new test_analyse();
			test.setScore(sdao.get_stu_score(stu_Id, test_Id_list.get(i)));
			test.setTest_Id(test_Id_list.get(i));
			test.setTest_name(sdao.get_test_name_by_test_Id(test_Id_list.get(i)));
			test.setAverage_score(sdao.get_average_score(test_Id_list.get(i)));
			test.setNum_all(sdao.get_num_all(test_Id_list.get(i)));
			test.setNum_before(sdao.get_num_before(stu_Id, test_Id_list.get(i)));
			//计算rate
			test.setRate((int)(((float)test.getNum_before()/(float)test.getNum_all())*100));
			System.out.println("num_before="+test.getNum_before()+" and num_all="+test.getNum_all());
			test_analyse_list.add(test);
		 }
		 return test_analyse_list;
		 
	}
	 /**
	  * 获取试卷中的单选题目列表
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static List<question> get_dx_list(int stu_Id,int test_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<question> timu_list=null;
		timu_list=sdao.get_dx_list(stu_Id, test_Id);
		return timu_list;
	 }
	 /**
	  * 获取试卷中的判断题目列表
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static List<question> get_pd_list(int stu_Id,int test_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<question> timu_list=null;
		timu_list=sdao.get_pd_list(stu_Id, test_Id);
		return timu_list;
	 }
	 /**
	  * 获取试卷中的简答题目列表
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static List<question_dt> get_dt_list(int stu_Id,int test_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<question_dt> timu_list=null;
		timu_list=sdao.get_dt_list(stu_Id, test_Id);
		return timu_list;
	 }
	 /**
	  * 获取学生密码
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static String get_stu_password(int stu_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		String stu_password=sdao.get_stu_password(stu_Id);
		return stu_password;
	 }
	 /**
	  * 更新学生密码
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static boolean update_stu_password(int stu_Id,String password){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		return (sdao.update_stu_password(stu_Id, password));
		
	 }
	 /**
	  * 获取学生考试状态
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static int get_test_state(int stu_Id,int test_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		return (sdao.get_test_state(stu_Id, test_Id));
		
	 }
	 /**
	  * 插入学生考试状态
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static boolean add_stu_test(int stu_Id,int test_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		return (sdao.add_stu_test(stu_Id, test_Id));
		
	 }
	 /**
	  * 查看学生申诉状态
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static boolean consult_exist(int stu_Id,int test_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		if(sdao.get_consult_list(stu_Id, test_Id).size()!=0){
			return true;
		}
		return false;
		
	 }
	 /**
	  * 插入学生申诉状态
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static boolean insert_consult(int stu_Id,int test_Id,String question){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		if(sdao.insert_consult(stu_Id, test_Id, question)){
			return true;
		}
		return false;
		
	 }
	 /**
	  * 得到学生申诉列表
	  * @param stu_Id
	  * @return
	  */
	 public static List<consult> get_consult_list(int stu_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		List<consult> consult_list=sdao.get_consult_list(stu_Id);
		return consult_list;
	 }
	 /**
	  * 插入客观题信息
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static boolean insert_keguan(int stu_Id,int test_Id){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		if(sdao.insert_keguan(stu_Id, test_Id))
			return true;
		return false;
	 }
	 /**
	  * 插入主观题信息
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static boolean insert_zhuguan(int stu_Id,int test_Id){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		studentDao sdao = (studentDao) context.getBean("studentDao");
		if(sdao.insert_zhuguan(stu_Id, test_Id))
			return true;
		return false;
	 }
	 
		/**
		 * 学生客观试卷信息是否已经存在
		 * @param stu_Id
		 * @param test_Id
		 * @return
		 */
		 public static boolean stu_test_keguan_exist(int stu_Id,int test_Id)
	    {
	        //处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			studentDao sdao = (studentDao) context.getBean("studentDao");
			List<stu_test> ss=null;
			ss=sdao.stu_test_keguan(stu_Id, test_Id);
			if(ss.size()==0)
			{
				return false;
			}else {
				return true;
			}
	    }
		 /**
		 * 学生主观试卷信息是否已经存在
		 * @param stu_Id
		 * @param test_Id
		 * @return
		 */
		 public static boolean stu_test_zhuguan_exist(int stu_Id,int test_Id)
	    {
	        //处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			studentDao sdao = (studentDao) context.getBean("studentDao");
			List<stu_test> ss=null;
			ss=sdao.stu_test_zhuguan(stu_Id, test_Id);
			if(ss.size()==0)
			{
				return false;
			}else {
				return true;
			}
	    }
 
	 
	 
	 
	 
	 
	 
	 
	 
}
