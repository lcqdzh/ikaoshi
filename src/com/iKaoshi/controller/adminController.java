package com.iKaoshi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.PlainDocument;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iKaoshi.bean.Student;
import com.iKaoshi.bean.Teacher;
import com.iKaoshi.bean.test;
import com.iKaoshi.service.adminService;
import com.iKaoshi.service.studentService;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Controller
public class adminController {
	/**
	 * 管理员登陆信息处理
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_login")
	public ModelAndView admin_login(HttpServletRequest request)
	{
		String idd = request.getParameter("username");
		int id=Integer.parseInt(idd);
        String password = request.getParameter("password");
        System.out.println(id);System.out.println(password);
        
        if(adminService.login(id, password))
        {
        	HttpSession session=request.getSession();
        	session.setAttribute("admin_Id", id);
        	//session.setAttribute("use_pas", id);
        	String admin_name=adminService.get_admin_name(id);
        	return new ModelAndView("admin_home","admin_name",admin_name);
        }
        return new ModelAndView("default");
	}
	/**
	 * 管理员登陆页面跳转处理
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_login_s")
	public String admin_login_s(HttpServletRequest request)
	{		
        return "admin_login_s";
	}
	/**
	 * 获取学生列表，返回stu_manage界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/stu_manage")
	public ModelAndView stu_manage(HttpServletRequest request)
	{
		List<Student> stu_list=adminService.get_stu_list();	
		return new ModelAndView("stu_manage","stu_list",stu_list);
        
	}
	/**
	 * 获取老师列表，返回tea_manage界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/tea_manage")
	public ModelAndView tea_manage(HttpServletRequest request)
	{
		List<Teacher> tea_list=adminService.get_tea_list();	
		return new ModelAndView("tea_manage","tea_list",tea_list);
        
	}
	/**
	 * 跳转到管理员主页
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_home")
	public ModelAndView admin_home(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int admin_Id=(int)session.getAttribute("admin_Id");
		System.out.println("session 中    admin_Id="+admin_Id);
		String admin_name=adminService.get_admin_name(admin_Id);
		
        return new ModelAndView("admin_home","admin_name",admin_name);
	}
	/**
	 * 跳转到管理员更新学生密码的界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_update_stu_password_page")
	public ModelAndView admin_update_stu_password(HttpServletRequest request)
	{		
		int stu_Id=Integer.parseInt(request.getParameter("stu_Id"));
		System.out.println("stu_Id="+stu_Id);
		return new ModelAndView("admin_update_stu_password_page","stu_Id",stu_Id);       
	}
	/**
	 * 跳转到管理员更新老师密码的界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_update_tea_password_page")
	public ModelAndView admin_update_tea_password(HttpServletRequest request)
	{		
		int tea_Id=Integer.parseInt(request.getParameter("tea_Id"));
		
		return new ModelAndView("admin_update_tea_password_page","tea_Id",tea_Id);       
	}
	/**
	 * 跳转到管理员更新自己的密码的界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_update_password_page")
	public ModelAndView admin_update_password(HttpServletRequest request)
	{
		return new ModelAndView("admin_update_password_page");       
	}
	/**
	 * 管理员更新学生密码操作
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_update_stu_password")
	public String update_stu_password_confirm(HttpServletRequest request,Model model)
	{	int stu_Id=Integer.parseInt(request.getParameter("stu_Id"));
		String new_password1=Integer.toString(stu_Id);
		
		//将新密码写到数据库
		studentService.update_stu_password(stu_Id, new_password1);
		
		//更新成功
		return "admin_update_password_success";
	}
	
	/**
	 * 管理员更新老师密码操作
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_update_tea_password")
	public String update_tea_password_confirm(HttpServletRequest request,Model model)
	{	int tea_Id=Integer.parseInt(request.getParameter("tea_Id"));
		String new_password1=Integer.toString(tea_Id);
		//将新密码写到数据库
		adminService.update_tea_password(tea_Id, new_password1);
		
		//更新成功
		return "admin_update_password_success";
	}
	/**
	 * 管理员更新自己密码操作
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_update_password")
	public ModelAndView update_password_confirm(HttpServletRequest request){
		HttpSession session=request.getSession();
		int admin_Id=(int)session.getAttribute("admin_Id");	
		String old_password=request.getParameter("old_password");
		System.out.println("old_pass="+old_password);
		String new_password1=request.getParameter("new_password1");
		String new_password2=request.getParameter("new_password2");
		if(!old_password.equals(adminService.get_admin_password(admin_Id))){
			String error="原密码输入错误，请重新输入！";
			return new ModelAndView("admin_update_password_page","error",error);
		}
		if(!new_password1.equals(new_password2)){
			String error="两次新密码不同，请重新输入";
			return new ModelAndView("admin_update_password_page","error",error);
		}
		//将新密码写到数据库
		adminService.update_admin_password(admin_Id, new_password1);
		
		//更新成功
		return new ModelAndView("admin_update_password_success");
	}
	/**
	 * 删除老师用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete_tea")
	public ModelAndView delete_tea(HttpServletRequest request)
	{	int tea_Id=Integer.parseInt(request.getParameter("tea_Id"));
		
		adminService.delete_tea(tea_Id);
		
		System.out.println("要删除的tea_Id="+tea_Id);
		
		List<Teacher> tea_list=adminService.get_tea_list();	
		return new ModelAndView("tea_manage","tea_list",tea_list);
	}
	/**
	 * 删除学生用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/delete_stu")
	public ModelAndView delete_stu(HttpServletRequest request)
	{	int stu_Id=Integer.parseInt(request.getParameter("stu_Id"));
		
		adminService.delete_stu(stu_Id);
		
		List<Student> stu_list=adminService.get_stu_list();	
		return new ModelAndView("stu_manage","stu_list",stu_list);
	}
	/**
	 * 跳转到管理员导入学生页
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_import_stu")
	public ModelAndView admin_import_stu(HttpServletRequest request)
	{	
		return new ModelAndView("admin_import_stu_page");
	}
	
	/**
	 * 跳转到管理员导入老师页
	 * @param request
	 * @return
	 */
	@RequestMapping("/admin_import_tea")
	public ModelAndView admin_import_tea(HttpServletRequest request)
	{	
		return new ModelAndView("admin_import_tea_page");
	}
	/**
	 * 导入学生名单
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/import_stu", method = RequestMethod.POST)
    public ModelAndView import_stu(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response) throws IOException{
        
		String info="导入失败!请检查名单内容和文件格式";
        //判断文件是否为空
        if(file==null) return null;
        //获取文件名
        String name=file.getOriginalFilename();
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0) return null;
        System.out.println(name);
        //批量导入。参数：文件名，文件。
        //也可以用request获取上传文件  
        //MultipartFile  fileFile = request.getFile("file"); //这里是页面的name属性   
        //转换成输入流  
       try {
	    	InputStream is = file.getInputStream();  
	        //得到excel  
	        Workbook workbook = Workbook.getWorkbook(is);  
	        //得到sheet  
	        Sheet sheet = workbook.getSheet(0);  
	         
	        //得到行数  
	        int rowsNum = sheet.getRows();  
	        //单元格  
	        Cell cell;  
	          
	        for (int i = 1; i < rowsNum; i++) {//我的excel第一行是标题,所以 i从1开始   	        	
                cell=sheet.getCell(0, i); //获取第一行 第0列的元素
                String str=cell.getContents();
                int stu_id=str.isEmpty()?0:Integer.parseInt(str);
                
                cell = sheet.getCell(1, i);//get question_conten  
                str=cell.getContents();
                String stu_name=str;
                if(stu_id!=0 && (!stu_name.isEmpty()))
                {
                	System.out.println("stu_id="+stu_id+" stu_name="+stu_name);
                	
                	
                	Student stu=new Student();
                	stu.setId(stu_id);
                	stu.setPassword(""+stu_id);
                	stu.setStu_name(stu_name);

                	
                	if(!adminService.query_stu(stu_id))
                	{
                		adminService.add_student(stu);
                		 info="导入成功！";
                	}
                	
                }
                
        }  
        //做你需要的操作  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } catch (BiffException e) {  
	        e.printStackTrace();  
	    }  
	      

       return new ModelAndView("admin_import_stu_page","info",info);
	}
	/**
	 * 导入老师名单
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/import_tea", method = RequestMethod.POST)
    public ModelAndView import_tea(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response) throws IOException{
        
		String info="导入失败!请检查名单内容和文件格式";
        //判断文件是否为空
        if(file==null) return null;
        //获取文件名
        String name=file.getOriginalFilename();
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
        long size=file.getSize();
        if(name==null || ("").equals(name) && size==0) return null;
        System.out.println(name);
        //批量导入。参数：文件名，文件。
        //也可以用request获取上传文件  
        //MultipartFile  fileFile = request.getFile("file"); //这里是页面的name属性   
        //转换成输入流  
       try {
	    	InputStream is = file.getInputStream();  
	        //得到excel  
	        Workbook workbook = Workbook.getWorkbook(is);  
	        //得到sheet  
	        Sheet sheet = workbook.getSheet(0);  
	       
	        //得到行数  
	        int rowsNum = sheet.getRows();  
	        //单元格  
	        Cell cell;  
	        
	        for (int i = 1; i < rowsNum; i++) {//我的excel第一行是标题,所以 i从1开始   	        	
                cell=sheet.getCell(0, i); //获取第一行 第0列的元素
                String str=cell.getContents();
                int tea_Id=str.isEmpty()?0:Integer.parseInt(str);
                
                cell = sheet.getCell(1, i);//get question_conten  
                str=cell.getContents();
                String tea_name=str;
                if(tea_Id!=0 && (!tea_name.isEmpty()))
                {
                	System.out.println("tea_Id="+tea_Id+" stu_name="+tea_name);
                	
                	
                	Teacher tea=new Teacher();
                	tea.setTea_ID(tea_Id);
                	tea.setTea_name(tea_name);
                	tea.setPassword(""+tea_Id);

                	if(!adminService.query_tea(tea_Id))
                	{
                		adminService.add_tea(tea);
                		 info="导入成功！";
                	}
                	
                }
                
        }  
        //做你需要的操作  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } catch (BiffException e) {  
	        e.printStackTrace();  
	    }  
	      

       return new ModelAndView("admin_import_tea_page","info",info);
	}

	
	
}
