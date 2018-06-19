package com.iKaoshi.service;
import java.sql.Timestamp;
// create by lcq
import java.util.List;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iKaoshi.bean.Question;
import com.iKaoshi.bean.Shijuanzhuguan;
import com.iKaoshi.bean.Student;
import com.iKaoshi.bean.TeaTestInfo;
import com.iKaoshi.bean.Teacher;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.dao.pigaiDao;
import com.iKaoshi.dao.questionDao;
import com.iKaoshi.dao.studentDao;
import com.iKaoshi.dao.teacherDao;
import com.iKaoshi.dao.teatestinfoDao;
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
	 //教师添加考试信息
	 //create by lcq 2018年6月18日16:21:06
	 public static boolean addteatestinfo(TeaTestInfo t)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		teatestinfoDao tdao = (teatestinfoDao) context.getBean("teatestinfoDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		return tdao.addTeatestinfo(t);
	 }
	 //获取最大的考试号
	 //create by lcq 2018年6月17日16:11:44
	 public static int getMaxtestid()
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 teatestinfoDao tdao = (teatestinfoDao) context.getBean("teatestinfoDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		return tdao.getMaxtestid();
	 }
	 //根据老师工号查看其所发布的考试
	 //create by lcq 2018年6月18日17:03:44
	 public static List<TeaTestInfo> quaryTestinfobyteaid(int tea_id)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 teatestinfoDao tdao = (teatestinfoDao) context.getBean("teatestinfoDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		return tdao.quarybyteaid(tea_id);
	 }
	 //根据考试编号获取考试信息
	 //create by lcq 2018年6月18日20:10:43
	 public static List<TeaTestInfo> quaryTestinfobytestid(int test_id)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 teatestinfoDao tdao = (teatestinfoDao) context.getBean("teatestinfoDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		return tdao.quarybytestid(test_id);
	 }
	 //根据某个试题信息更新
	 //create by lcq 2018年6月18日20:57:45
	 public static boolean updateteatestinfobyone(TeaTestInfo t)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 teatestinfoDao tdao = (teatestinfoDao) context.getBean("teatestinfoDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		return tdao.updateteatestinfobyone(t);
	 }
	 //根据学生的学号与考试编号产生试题
	 //create by lcq 2018年6月19日00:01:43
	 public static boolean getkaoshi(int stu_Id,int test_Id)
	 {
		 
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 teatestinfoDao tdao = (teatestinfoDao) context.getBean("teatestinfoDao");
		 questionDao qdao = (questionDao) context.getBean("questionDao"); 
		 TeaTestInfo t=new TeaTestInfo();
		 t=null;
		 List<TeaTestInfo> tti_l=null;
		 tti_l=tdao.quarybytestid(test_Id);
		 t=tti_l.get(0);

		 if(t.getDx_easy()!=0)//单选简单题
		 {
			 List<Question> temp=null;//中间变量
			 temp=qdao.quarybytikuidquestiontypelable(t.getTiku_id(), 1, 1);
			 int[] a=new int[t.getDx_easy()];
			 a= suiji.getRandomFromArray(temp.size(),t.getDx_easy());
			 System.out.println("here");
			 System.out.println(t.getDx_easy());
			 for(int i=0;i<t.getDx_easy();i++)
			 {
				 Question qq=new Question();
				 qq=temp.get(a[i]);
				 System.out.println(qq.toString());
				 qdao.addQuestionkg(stu_Id, test_Id, qq.getQuestion_Id(), qq.getTiku_Id());
			 }
		 }
		 if(t.getDx_medium()!=0)//单选中等题
		 {
			 List<Question> temp=null;//中间变量
			 temp=qdao.quarybytikuidquestiontypelable(t.getTiku_id(), 1, 2);
			 int[] a=new int[t.getDx_medium()];
			 a= suiji.getRandomFromArray(temp.size(),t.getDx_medium());
			 System.out.println("here");
			 System.out.println(t.getDx_medium());
			 for(int i=0;i<t.getDx_medium();i++)
			 {
				 Question qq=new Question();
				 qq=temp.get(a[i]);
				 System.out.println(qq.toString());
				 qdao.addQuestionkg(stu_Id, test_Id, qq.getQuestion_Id(), qq.getTiku_Id());
			 }
		 }
		 if(t.getDx_hard()!=0)//单选难题
		 {
			 List<Question> temp=null;//中间变量
			 temp=qdao.quarybytikuidquestiontypelable(t.getDx_hard(), 3, 1);
			 int[] a=new int[t.getDx_hard()];
			 a= suiji.getRandomFromArray(temp.size(),t.getDx_hard());
			 System.out.println("here");
			 System.out.println(t.getDx_hard());
			 for(int i=0;i<t.getDx_hard();i++)
			 {
				 Question qq=new Question();
				 qq=temp.get(a[i]);
				 System.out.println(qq.toString());
				 qdao.addQuestionkg(stu_Id, test_Id, qq.getQuestion_Id(), qq.getTiku_Id());
			 }
		 }
		 if(t.getPd_easy()!=0)//判断简单题
		 {
			 List<Question> temp=null;//中间变量
			 temp=qdao.quarybytikuidquestiontypelable(t.getPd_easy(), 3, 1);
			 int[] a=new int[t.getPd_easy()];
			 a= suiji.getRandomFromArray(temp.size(),t.getPd_easy());
			 System.out.println("here");
			 System.out.println(t.getPd_easy());
			 for(int i=0;i<t.getPd_easy();i++)
			 {
				 Question qq=new Question();
				 qq=temp.get(a[i]);
				 System.out.println(qq.toString());
				 qdao.addQuestionkg(stu_Id, test_Id, qq.getQuestion_Id(), qq.getTiku_Id());
			 }
		 }
		 if(t.getPd_medium()!=0)//判断中等题
		 {
			 List<Question> temp=null;//中间变量
			 temp=qdao.quarybytikuidquestiontypelable(t.getPd_medium(), 3, 1);
			 int[] a=new int[t.getPd_medium()];
			 a= suiji.getRandomFromArray(temp.size(),t.getPd_medium());
			 System.out.println("here");
			 System.out.println(t.getPd_medium());
			 for(int i=0;i<t.getPd_medium();i++)
			 {
				 Question qq=new Question();
				 qq=temp.get(a[i]);
				 System.out.println(qq.toString());
				 qdao.addQuestionkg(stu_Id, test_Id, qq.getQuestion_Id(), qq.getTiku_Id());
			 }
		 }
		 if(t.getPd_hard()!=0)//判断难题
		 {
			 List<Question> temp=null;//中间变量
			 temp=qdao.quarybytikuidquestiontypelable(t.getPd_hard(), 3, 1);
			 int[] a=new int[t.getPd_hard()];
			 a= suiji.getRandomFromArray(temp.size(),t.getPd_hard());
			 System.out.println("here");
			 System.out.println(t.getPd_hard());
			 for(int i=0;i<t.getPd_hard();i++)
			 {
				 Question qq=new Question();
				 qq=temp.get(a[i]);
				 System.out.println(qq.toString());
				 qdao.addQuestionkg(stu_Id, test_Id, qq.getQuestion_Id(), qq.getTiku_Id());
			 }
		 }
		 if(t.getDt_easy()!=0)//大题简单题
		 {
			 List<Question> temp=null;//中间变量
			 temp=qdao.quarybytikuidquestiontypelable(t.getTiku_id(), 3, 1);
			 int[] a=new int[t.getDt_easy()];
			 a= suiji.getRandomFromArray(temp.size(),t.getDt_easy());
			 System.out.println("here");
			 System.out.println(t.getDt_easy());
			 for(int i=0;i<t.getDt_easy();i++)
			 {
				 Question qq=new Question();
				 qq=temp.get(a[i]);
				 System.out.println(qq.toString());
				 qdao.addQuestionzg(stu_Id, test_Id, qq.getQuestion_Id(), qq.getTiku_Id());
			 }
		 }
		 if(t.getDt_medium()!=0)//大题中等题
		 {
			 List<Question> temp=null;//中间变量
			 temp=qdao.quarybytikuidquestiontypelable(t.getDt_medium(), 3, 1);
			 int[] a=new int[t.getDt_medium()];
			 a= suiji.getRandomFromArray(temp.size(),t.getDt_medium());
			 System.out.println("here");
			 System.out.println(t.getDt_medium());
			 for(int i=0;i<t.getDt_medium();i++)
			 {
				 Question qq=new Question();
				 qq=temp.get(a[i]);
				 System.out.println(qq.toString());
				 qdao.addQuestionzg(stu_Id, test_Id, qq.getQuestion_Id(), qq.getTiku_Id());
			 }
		 }
		 if(t.getDt_hard()!=0)//大题难题
		 {
			 List<Question> temp=null;//中间变量
			 temp=qdao.quarybytikuidquestiontypelable(t.getDt_hard(), 3, 1);
			 int[] a=new int[t.getDt_hard()];
			 a= suiji.getRandomFromArray(temp.size(),t.getDt_hard());
			 System.out.println("here");
			 System.out.println(t.getDt_hard());
			 for(int i=0;i<t.getDt_easy();i++)
			 {
				 Question qq=new Question();
				 qq=temp.get(a[i]);
				 System.out.println(qq.toString());
				 qdao.addQuestionzg(stu_Id, test_Id, qq.getQuestion_Id(), qq.getTiku_Id());
			 }
		 }

		
		 return true;
	 }
	 //根据教师id与日期查看已经结束的考试
	 //create by lcq 2018年6月19日10:52:01
	 public static List<TeaTestInfo> quaryOverbyteaiddate(int tea_id,Timestamp today)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 teatestinfoDao tdao = (teatestinfoDao) context.getBean("teatestinfoDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		return tdao.quaryOverbyteaiddate(tea_id, today);
	 }
	 //根据教师id与日期查看未结束的考试
	 //create by lcq 2018年6月19日10:54:57
	 public static List<TeaTestInfo> quaryNOverbyteaiddate(int tea_id,Timestamp today)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 teatestinfoDao tdao = (teatestinfoDao) context.getBean("teatestinfoDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		return tdao.quaryNOverbyteaiddate(tea_id, today);
	 }
	 //根据考试编号查看学生作答的主观题
	 //create by lcq 2018年6月19日15:40:35
	 public static List<Shijuanzhuguan> quaryBytestid(int test_id)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 pigaiDao pdao = (pigaiDao) context.getBean("pigaiDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		return pdao.quaryBytestid(test_id);
	 }
	 //根据学号 考试号 试题号获取学生答题   //应该是返回的是一个对象
	 //create by lcq 2018年6月19日20:34:54
	 public static Shijuanzhuguan getBytestidstuidquestionidtikuid(int test_id,int stu_id,int question_id,int tiku_id)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 pigaiDao pdao = (pigaiDao) context.getBean("pigaiDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		 List<Shijuanzhuguan> sl=null;
		 sl=pdao.getBytestidstuidquestionidtikuid(test_id, stu_id, question_id, tiku_id);
		 Shijuanzhuguan ns=new Shijuanzhuguan();
		 if(sl.size()!=0)
		 {
			 ns=sl.get(0);
		 }
		 return ns;
	 }
	 //更新学生的大题信息
	 //create by lcq 2018年6月19日21:45:25
	 public static boolean updatebysjzg(Shijuanzhuguan s)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 pigaiDao pdao = (pigaiDao) context.getBean("pigaiDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		 return pdao.updatebysjzg(s);
	 }
	 //根据学号 考试号 试题号获取学生答题 前一个   //应该是返回的是一个对象
	 //create by lcq 2018年6月19日20:34:54
	 public static Shijuanzhuguan getBytestidstuidquestionidtikuid_p(int test_id,int stu_id,int question_id,int tiku_id,Shijuanzhuguan s)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 pigaiDao pdao = (pigaiDao) context.getBean("pigaiDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		 List<Shijuanzhuguan> sl=null;
		 sl=pdao.quaryBytestidstuidquestionidtikuid_xiangtongxuehaos(test_id, stu_id, question_id, tiku_id);//获取相同学号下的lsit
		 Shijuanzhuguan ns=new Shijuanzhuguan();
		 if(sl.size()!=0)//存在 就取第一个
		 {
			 ns=sl.get(0);
		 }
		 else 
		 {   //不存在 取不同学号的
			 List<Shijuanzhuguan> sl2=null;
			 sl2=pdao.quaryBytestidstuidtikuid_butongxuehaos(test_id, stu_id, tiku_id);
			 if(sl2.size()!=0) //存在 就取第一个
			 {
				 ns=sl2.get(0);
			 }
			 else {
				 ns=s; //不存在 就改为原来的
			 }
		 }
		 return ns;
	 }
	//根据学号 考试号 试题号获取学生答题 后一个   //应该是返回的是一个对象
	 //create by lcq 2018年6月19日20:34:54
	 public static Shijuanzhuguan getBytestidstuidquestionidtikuid_n(int test_id,int stu_id,int question_id,int tiku_id,Shijuanzhuguan s)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		 pigaiDao pdao = (pigaiDao) context.getBean("pigaiDao");
		//System.out.println("max"+qdao.getMaxquestionidBytikuid(1));
		 List<Shijuanzhuguan> sl=null;
		 sl=pdao.quaryBytestidstuidquestionidtikuid_xiangtongxuehaob(test_id, stu_id, question_id, tiku_id);//获取相同学号下的lsit
		 Shijuanzhuguan ns=new Shijuanzhuguan();
		 if(sl.size()!=0)//存在 就取第一个
		 {
			 ns=sl.get(0);
		 }
		 else 
		 {   //不存在 取不同学号的
			 List<Shijuanzhuguan> sl2=null;
			 sl2=pdao.quaryBytestidstuidtikuid_butongxuehaob(test_id, stu_id, tiku_id);
			 if(sl2.size()!=0) //存在 就取第一个
			 {
				 ns=sl2.get(0);
			 }
			 else {
				 ns=s; //不存在 就改为原来的
			 }
		 }
		 return ns;
	 }
	 //查看学生某门考试是否还有老师未批改的试题 有true 没有false
	 //create by lcq 2018年6月20日00:05:17
	 public static boolean getBytestidstuidtikuid_wpg(int test_id,int stu_id,int tiku_id)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 pigaiDao pdao = (pigaiDao) context.getBean("pigaiDao");
		 List<Shijuanzhuguan> sl=null;
		 sl=pdao.getBytestidstuidtikuid_wpg(test_id, stu_id, tiku_id);
		 if(sl.size()==0) {
			 return false;
		 }
		 else {
			 return true;
		 }
	 }
	 //返回批改完的list
	 //create by lcq 2018年6月20日00:20:46
	 public static List<Shijuanzhuguan> getBytestidstuidtikuid_pg(int test_id,int stu_id,int tiku_id)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 pigaiDao pdao = (pigaiDao) context.getBean("pigaiDao");
		 List<Shijuanzhuguan> sl=null;
		 return sl=pdao.getBytestidstuidtikuid_pg(test_id, stu_id, tiku_id);
	 }
	 //更新学生考试信息
	 //create by lcq 2018年6月20日00:24:01
	 public static boolean updateStutestinfo(int stu_id,int test_id,int tiku_id)
	 {
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 pigaiDao pdao = (pigaiDao) context.getBean("pigaiDao");
		 List<Shijuanzhuguan> sl=null;
		 sl=pdao.getBytestidstuidtikuid_pg(test_id, stu_id, tiku_id);
		 int score=0;
		 for(int i=0;i<sl.size();i++)
		 {
			 score=sl.get(i).getScore()+score;
		 }
		 return pdao.updateStutestinfo(stu_id, test_id, score);	 
	 }
	 
}
