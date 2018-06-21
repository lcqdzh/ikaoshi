package com.iKaoshi.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iKaoshi.bean.Student;
import com.iKaoshi.bean.Teacher;
import com.iKaoshi.dao.adminDao;
import com.iKaoshi.dao.studentDao;;

public class adminService {
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
		adminDao sdao = (adminDao) context.getBean("adminDao");
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
	  * 根据学号得到管理员名字
	  * @param stu_Id
	  * @return
	  */
	 public static String get_admin_name(int stu_Id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 adminDao sdao = (adminDao) context.getBean("adminDao");
		 String admin_name=sdao.get_admin_name(stu_Id);
		 return admin_name;
	 }
	 /**
	  * 根据所有学生信息列表
	  * @param 
	  * @return
	  */
	 public static List<Student> get_stu_list(){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 adminDao sdao = (adminDao) context.getBean("adminDao");
		 List<Student> stu_list=sdao.stu_list();
		 return stu_list;
	 }
	 /**
	  * 根据所有老师信息列表
	  * @param 
	  * @return
	  */
	 public static List<Teacher> get_tea_list(){
		 ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		 //从ioc容器中获取dao
		 adminDao sdao = (adminDao) context.getBean("adminDao");
		 List<Teacher> tea_list=sdao.tea_list();
		 return tea_list;
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
		adminDao sdao = (adminDao) context.getBean("adminDao");
		return (sdao.update_stu_password(stu_Id, password));
		
	 }
	 /**
	  * 更新老师密码
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static boolean update_tea_password(int tea_Id,String password){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		adminDao sdao = (adminDao) context.getBean("adminDao");
		return(sdao.update_tea_password(tea_Id, password));
		
	 }
	 /**
	  * 获取管理员密码
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static String get_admin_password(int admin_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		adminDao sdao = (adminDao) context.getBean("adminDao");
		String admin_password=sdao.get_admin_password(admin_Id);
		return admin_password;
	 }
	 /**
	  * 更新管理员密码
	  * @param stu_Id
	  * @param test_Id
	  * @return
	  */
	 public static boolean update_admin_password(int admin_Id,String password){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		adminDao sdao = (adminDao) context.getBean("adminDao");
		return (sdao.update_admin_password(admin_Id, password));
		
	 }
	 /**
	  * 删除老师用户
	  * @param tea_Id
	  * @return
	  */
	 public static boolean delete_tea(int tea_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		adminDao sdao = (adminDao) context.getBean("adminDao");
		return (sdao.delete_tea(tea_Id));
		
	 }
	 /**
	  * 删除老师用户
	  * @param tea_Id
	  * @return
	  */
	 public static boolean delete_stu(int stu_Id){
		 //处理业务逻辑
	 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		//从ioc容器中获取dao
		adminDao sdao = (adminDao) context.getBean("adminDao");
		return (sdao.delete_stu(stu_Id));
		
	 }
	 /**
	  * 查看学生信息是否存在 存在ture 不存在false
	  * @param stu_id
	  * @return
	  */
	 
	 public static boolean  query_stu(int stu_id)
	 {
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			adminDao sdao = (adminDao) context.getBean("adminDao");
			List<Student> sl=null;
			sl=sdao.query_stu(stu_id);
			if(sl.size()==0)
			{
				return false;
			}else {
				return true;
			}
	 }
	 /**
	  * 插入学生信息
	  * @param s
	  * @return
	  */
	 public static boolean add_student(Student s){
		 //处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			adminDao sdao = (adminDao) context.getBean("adminDao");
			return sdao.add_student(s);	
	 }
	 /**
	  * 查看老师信息是否存在 存在ture 不存在false
	  * @param stu_id
	  * @return
	  */
	 
	 public static boolean  query_tea(int tea_Id)
	 {
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			adminDao sdao = (adminDao) context.getBean("adminDao");
			List<Teacher> sl=null;
			sl=sdao.query_tea(tea_Id);
			if(sl.size()==0)
			{
				return false;
			}else {
				return true;
			}
	 }
	 /**
	  * 插入老师信息
	  * @param s
	  * @return
	  */
	 public static boolean add_tea(Teacher s){
		 //处理业务逻辑
		 	ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
			//从ioc容器中获取dao
			adminDao sdao = (adminDao) context.getBean("adminDao");
			return sdao.add_tea(s);	
	 }


}
