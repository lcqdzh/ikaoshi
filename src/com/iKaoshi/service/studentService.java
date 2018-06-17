package com.iKaoshi.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iKaoshi.dao.studentDao;

import com.iKaoshi.bean.*;
public class studentService {
	
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
}
