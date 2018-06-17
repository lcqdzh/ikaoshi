package com.iKaoshi.service;
// create by lcq
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iKaoshi.bean.Question;
import com.iKaoshi.bean.Student;
import com.iKaoshi.bean.Teacher;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.dao.questionDao;
import com.iKaoshi.dao.studentDao;
import com.iKaoshi.dao.teacherDao;
import com.iKaoshi.dao.tikuDao;

public class teacherService {
	//教师登录
	 public static boolean login(int id,String password)
	 {
	        //处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			teacherDao sdao = (teacherDao) context.getBean("teacherDao");
			List<Teacher> ss=null;
			ss=sdao.login(id, password);
			if(ss.size()==0)
			{
				return false;
			}else {
				return true;
			}
	 }
	 //获取题库信息
	 //create by lcq 2018年6月16日08:42:24
	 public static List<Tikuxinxi> quary(int tea_id)
	 {
		//处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			tikuDao sdao = (tikuDao) context.getBean("tikuDao");
			List<Tikuxinxi> ss=null;
			ss=sdao.quary(tea_id);
			return ss;
	 }
	 
	 //添加题库信息
	 // create by lcq 2018年6月15日22:57:05
	 public static boolean addTikuxinxi(Tikuxinxi t)
	 {
		//处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			tikuDao sdao = (tikuDao) context.getBean("tikuDao");
			return sdao.addTikuxinxi(t);
			
	 }
	 
	 
	 //根据题库编号获取题库信息
	 // create by lcq 2018年6月16日21:56:40
	 public static List<Question> quaryQuestionbytikuID(int tiku_Id)
	 {
		//处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			questionDao qdao = (questionDao) context.getBean("questionDao");
			return qdao.quarybytikuid(tiku_Id);
			
	 }
	 //根据题目的编号获取题目信息
	 //create by lcq 2018年6月16日21:59:02
	 public static List<Question> quaryQuestionbyquestionID(int question_Id,int tiku_Id)
	 {
		//处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			questionDao qdao = (questionDao) context.getBean("questionDao");
			return qdao.quarybyquestionIdandtikuId(question_Id, tiku_Id);
	 }
	//修改某到题目的信息
	 // create by lcq 2018年6月17日15:02:09
	 public static boolean updatebyone(Question q)
	 {
		//处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			questionDao qdao = (questionDao) context.getBean("questionDao");
			//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
			return qdao.updatebyone(q);
			
	 }
	 //根据题库编号查看最大的题号
	 //create by lcq 2018年6月17日16:11:44
	 public static int getMaxquestion(int tiku_Id)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		questionDao qdao = (questionDao) context.getBean("questionDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		return qdao.getMaxquestionidBytikuid(tiku_Id);
	 }
	//添加题库中的题
	 //create by lcq 2018年6月17日16:11:44
	 public static boolean addquestion(Question q)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		questionDao qdao = (questionDao) context.getBean("questionDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		return qdao.addQuestion(q);
	 }
	 //根据题库编号与试题编号删除某一试题
	 public static boolean delquestion(int question_Id,int tiku_Id)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 questionDao qdao = (questionDao) context.getBean("questionDao"); 
		 return qdao.delateQuestion(question_Id, tiku_Id);
	 }
}
