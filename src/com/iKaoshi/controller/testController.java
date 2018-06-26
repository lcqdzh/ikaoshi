package com.iKaoshi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.iKaoshi.bean.test_analyse;
import com.iKaoshi.bean.consult;
import com.iKaoshi.bean.question;
import com.iKaoshi.bean.question_dt;
import com.iKaoshi.bean.test;
import com.iKaoshi.service.*;
@Controller
public class testController {
	/**
	 * 跳转到学生信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/stu_info")
	public ModelAndView stu_info(HttpServletRequest request)
	{
		
        return new ModelAndView("stu_info");
	}
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();
        return new ModelAndView("logout");
	}
	
	/**
	 * 学生登陆信息处理
	 * @param request
	 * @return
	 */
	@RequestMapping("/stu_login")
	public ModelAndView stu_login(HttpServletRequest request)
	{
		System.out.println("123");
		String idd = request.getParameter("username");
		int id=Integer.parseInt(idd);
        String password = request.getParameter("password");
        System.out.println(id);System.out.println(password);
        
        if(studentService.login(id, password))
        {
        	HttpSession session=request.getSession();
        	session.setAttribute("stu_Id", id);
        	//session.setAttribute("use_pas", id);
        	String str=studentService.get_stu_name(id);
        	return new ModelAndView("stu_home","stu_name",str);
        }
        return new ModelAndView("default");
	}
	/**
	 * 学生登陆页面跳转处理
	 * @param request
	 * @return
	 */
	@RequestMapping("/stu_login_s")
	public ModelAndView stu_login_s(HttpServletRequest request)
	{		
        return new ModelAndView("stu_login_s");
	}

	/**
	 * 跳转到考试确认界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/enter_test")
	public ModelAndView enter_test(HttpServletRequest request)
	{
		int test_Id=Integer.parseInt(request.getParameter("test_Id"));
		HttpSession session=request.getSession();
		session.setAttribute("test_Id", test_Id);
		
        return new ModelAndView("enter_test","error",test_Id);
	}
	/**
	 * 跳转到显示考试详情，传递参数列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/show_test_detail")
	public String show_test_detail(HttpServletRequest request,Model model)
	{
		int test_Id=Integer.parseInt(request.getParameter("test_Id"));
		HttpSession session=request.getSession();
		int stu_Id=(int)session.getAttribute("stu_Id");
		List<question> dx_list=studentService.get_dx_list(stu_Id, test_Id);
		List<question> pd_list=studentService.get_pd_list(stu_Id, test_Id);
		List<question_dt> dt_list=studentService.get_dt_list(stu_Id, test_Id);
		model.addAttribute("dx_list", dx_list);
		model.addAttribute("pd_list", pd_list);
		model.addAttribute("dt_list", dt_list);
		//后期添加
		model.addAttribute("test_Id",test_Id);
		
        return "show_test_detail";
	}
	/**
	 * 跳转到申诉界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/consult")
	public ModelAndView consult(HttpServletRequest request)
	{
		int test_Id=Integer.parseInt(request.getParameter("test_Id"));
		HttpSession session=request.getSession();		
		session.setAttribute("test_Id", test_Id);
		
        return new ModelAndView("consult");
	}
	/**
	 * 申诉的相关处理
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/consult_process")
	public ModelAndView consult_process(HttpServletRequest request) throws UnsupportedEncodingException
	{	
		String question = new String(request.getParameter("consult")); 
		//String question = new String(request.getParameter("consult").getBytes("iso-8859-1"), "utf-8"); 		
		System.out.println("question="+question);
		HttpSession session=request.getSession();		
		int test_Id=(int)session.getAttribute("test_Id");
		int stu_Id=(int)session.getAttribute("stu_Id");
		//检查申诉是否存在
		if(studentService.consult_exist(stu_Id, test_Id)){
			String error="您的申诉次数已经用完！";
			return new ModelAndView("consult","error",error);
		}
		
		String error="申诉信息成功提交";
		//将申诉写入数据库，顺便更改状态
		studentService.insert_consult(stu_Id, test_Id, question);
		
		return new ModelAndView("consult","error",error);
	}
	/**
	 * 跳转到显示申诉界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/my_consult")
	public ModelAndView my_consult(HttpServletRequest request)
	{		
		HttpSession session=request.getSession();		
		int stu_Id=(int)session.getAttribute("stu_Id");
		List<consult> consult_list=studentService.get_consult_list(stu_Id);
        return new ModelAndView("show_consult","consult_list",consult_list);
	}
	
	
	
	
	/**
	 * 	跳转到待考试列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/stu_test_list")
	public ModelAndView stu_test_list(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int stu_Id=(int)session.getAttribute("stu_Id");
		List<test> stu_test_list=studentService.get_stu_test_list(stu_Id);
        return new ModelAndView("stu_test_list","stu_test_list",stu_test_list);
	}
	/**
	 * 	跳转到显示之前所有的考试列表界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/stu_testd_list")
	public String stu_testd_list(HttpServletRequest request,Model model)
	{
		HttpSession session=request.getSession();
		int stu_Id=(int)session.getAttribute("stu_Id");
		List<test> stu_testd_list=studentService.get_stu_testd_list(stu_Id);
		List<test> stu_score_done_list=studentService.get_stu_score_done_list(stu_Id);
		
		model.addAttribute("stu_testd_list",stu_testd_list);
		model.addAttribute("stu_score_done_list", stu_score_done_list);
		
        return "stu_testd_list";
	}
	/**
	 * 	跳转到尚未开始的考试列表界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/not_begin_list")
	public ModelAndView stu_not_begin_list(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int stu_Id=(int)session.getAttribute("stu_Id");
		List<test> not_begin_list=studentService.get_not_begin_list(stu_Id);
        return new ModelAndView("not_begin_list","not_begin_list",not_begin_list);
	}
	/**
	 * 	跳转到已经过期的考试列表界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/overdue_list")
	public ModelAndView stu_overd_list(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int stu_Id=(int)session.getAttribute("stu_Id");
		List<test> overdue_list=studentService.get_overdue_list(stu_Id);
        return new ModelAndView("overdue_list","overdue_list",overdue_list);
	}
	
	
	
	/**
	 * 跳转到答题界面，需要把题目信息传过去，要知道用户id和test_Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/test_going")
	public String test_going(HttpServletRequest request,Model model)
	{
		HttpSession session=request.getSession();
		
		int stu_Id=(int)session.getAttribute("stu_Id");
		int test_Id=(int)session.getAttribute("test_Id");
		
		//先要判断是否存在学生考试信息，不存在的话插入，存在的话直接读出来
		if(!studentService.stu_test_zhuguan_exist(stu_Id, test_Id)){
			studentService.insert_zhuguan(stu_Id, test_Id);
		}
		if(!studentService.stu_test_keguan_exist(stu_Id, test_Id)){
			studentService.insert_keguan(stu_Id, test_Id);
		}
		
		
		//进入之前先要查看学生考试状态，如果是1就重新显示考试列表进行刷新
		if(studentService.get_test_state(stu_Id, test_Id)==1){
			List<test> stu_test_list=studentService.get_stu_test_list(stu_Id);
			model.addAttribute("stu_test_list",stu_test_list);
	        return "stu_test_list";
		}
		
		//设置学生考试state=1,中途退出就GG了
		studentService.set_stu_test_state(stu_Id,test_Id,1);
		
		
		
		int hour=studentService.get_test_hour(test_Id);
		int minute=studentService.get_test_minute(test_Id);
		List<question> question_dx_list=studentService.get_question_dx(stu_Id, test_Id);
		List<question> question_pd_list=studentService.get_question_pd(stu_Id, test_Id);
		List<question> question_dt_list=studentService.get_question_dt(stu_Id, test_Id);
		model.addAttribute("question_dx_list",question_dx_list);
		model.addAttribute("question_pd_list",question_pd_list);
		model.addAttribute("question_dt_list",question_dt_list);
		model.addAttribute("hour",hour);
		model.addAttribute("minute",minute);
		
		System.out.println("dx_list_size="+question_dx_list.size());
		System.out.println("pd_list_size="+question_pd_list.size());
		System.out.println("dt_list_size="+question_dt_list.size());
        return "test_going";
	}
	/**
	 * 提交试卷进行客观题分数计算，并更新状态，麻烦
	 * @author perfect
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/test_submit")
	public String test_submit(HttpServletRequest request,Model model)
	{
		HttpSession session=request.getSession();
		
		int stu_Id=(int)session.getAttribute("stu_Id");
		int test_Id=(int)session.getAttribute("test_Id");
		int count_dx=studentService.get_count_dx(test_Id);
		int count_pd=studentService.get_count_pd(test_Id);
		int count_dt=studentService.get_count_dt(test_Id);
		System.out.println("count_dx="+count_dx+"  count_pd="+count_pd+"count_dt="+count_dt);
		for(int i=1;i<=count_dx;i++){
			//获取了问题题号
			int question_Id=Integer.parseInt(request.getParameter("ans"+Integer.toString(i)));
			String str_answer=request.getParameter("answer"+Integer.toString(i));
			if(str_answer==null){
				continue;
			}
			//获取了作答答案
			int stu_answer=Integer.parseInt(str_answer);
			//将答案写进数据库中，客观题
			System.out.println("question_Id="+question_Id+" and stu_answer="+stu_answer);
			studentService.set_keguan_answer(stu_Id,test_Id,question_Id,stu_answer);
		
			
			//获取标准答案
			int answer=studentService.get_keguan_answer(test_Id,question_Id);
			
			//将答案进行比对,相同的话进行对学生成绩进行加分，传入flag=1表示是选择题
			if(answer==stu_answer){
				studentService.cal_keguan_score(stu_Id,test_Id,1);
			}
			
		}
		for(int i=count_dx+1;i<=count_dx+count_pd;i++){
			//获取了问题题号
			int question_Id=Integer.parseInt(request.getParameter("ans"+Integer.toString(i)));
			String str_answer=request.getParameter("answer"+Integer.toString(i));
			if(str_answer==null)
				continue;
			//获取了作答答案
			int stu_answer=Integer.parseInt(str_answer);
			//将答案写进数据库中，客观题
			studentService.set_keguan_answer(stu_Id,test_Id,question_Id,stu_answer);
		
			
			//获取标准答案
			int answer=studentService.get_keguan_answer(test_Id,question_Id);
			
			//将答案进行比对,相同的话进行对学生成绩进行加分，传入flag=2表示是判断题
			if(answer==stu_answer){
				studentService.cal_keguan_score(stu_Id,test_Id,2);
			}
		}
		for(int i=count_dx+count_pd+1;i<=count_dx+count_pd+count_dt;i++){
			//获取了问题题号
			int question_Id=Integer.parseInt(request.getParameter("ans"+Integer.toString(i)));
			//获取了作答答案,这里大题答案是string类型的，不用转换啊兄弟
			String stu_answer=request.getParameter("answer"+Integer.toString(i));
			//将主观题答案写进数据库中，这里不需要计算分数
			studentService.set_zhuguan_answer(stu_Id,test_Id,question_Id,stu_answer);
		}
		
		if(count_dt!=0){
			//修改试卷状态为已提交，0表示未做，1表示已提交，2表示已批改，可以查成绩
			studentService.set_stu_test_state(stu_Id,test_Id,1);
			return "submit_done";
		}else{
			int score=studentService.get_stu_score(stu_Id,test_Id);
			model.addAttribute("score",score);
			studentService.set_stu_test_state(stu_Id,test_Id,2);
			return "submit_show_score";
		}
		
	}
	/**
	 * 跳转到添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/add_test")
	public ModelAndView add(HttpServletRequest request)
	{
		//String str="123";
        return new ModelAndView("add");
	}
	/**
	 * 跳转到添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView add_confirm(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int stu_Id=(int)session.getAttribute("stu_Id");
		
		
		String str=request.getParameter("test_Id");
		
		
		if(str.equals("")){
			String error="输入为空，请重新输入";
			return new ModelAndView("add","error",error);
		}
		
		
		int test_Id=Integer.parseInt(str);
		//判断是否已经添加了这门考试
		if(studentService.stu_test_exist(stu_Id, test_Id)){
		
			String error="您已经添加过该考试，请勿重复操作！";
			return new ModelAndView("add","error",error);
		}
		//判断是否有这个test_Id
		if(studentService.test_Id_exist(test_Id)){
			//调用函数进行在stu_test_info表中进行添加，已完成
			
			studentService.add_stu_test(stu_Id, test_Id);
			
			
			
			return new ModelAndView("add_success");
		}else{
			String error="您输入的考试号有误，请重新输入！";
			return new ModelAndView("add","error",error);
		}
		
	}
	
	/**
	 * 更新密码操作
	 * @param request
	 * @return
	 */
	@RequestMapping("/update_stu_password")
	public ModelAndView update_password_confirm(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int stu_Id=(int)session.getAttribute("stu_Id");
		String old_password=request.getParameter("old_password");
		String new_password1=request.getParameter("new_password1");
		String new_password2=request.getParameter("new_password2");
		if(!old_password.equals(studentService.get_stu_password(stu_Id))){
			String error="原密码输入错误，请重新输入！";
			return new ModelAndView("stu_info","error",error);
		}
		if(!new_password1.equals(new_password2)){
			String error="两次新密码不同，请重新输入";
			return new ModelAndView("stu_info","error",error);
		}
		//将新密码写到数据库
		studentService.update_stu_password(stu_Id, new_password1);
		
		//更新成功
		return new ModelAndView("update_password_success");
	}
	/**
	 * 跳转到成绩分析页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/test_analyse")
	public ModelAndView test_analyse(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int stu_Id=(int)session.getAttribute("stu_Id");
		//这里要根据学号得到一个test_analyse列表
		List<test_analyse> test_analyse_list=studentService.get_test_analyse_list(stu_Id);
		
        return new ModelAndView("test_analyse","test_analyse_list",test_analyse_list);
	}
	
	/**
	 * 跳转到学生主页
	 * @param request
	 * @return
	 */
	@RequestMapping("/stu_home")
	public ModelAndView stu_home(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		int stu_Id=(int)session.getAttribute("stu_Id");
		System.out.println("session 中    stu_Id="+stu_Id);
		String stu_name=studentService.get_stu_name(stu_Id);
		
        return new ModelAndView("stu_home","stu_name",stu_name);
	}
	
	
	
	@RequestMapping(value = "batchimport", method = RequestMethod.POST)
    public String batchimport(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response) throws IOException{
        
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
       try { InputStream is = file.getInputStream();  
        //得到excel  
        Workbook workbook = Workbook.getWorkbook(is);  
        //得到sheet  
        Sheet sheet = workbook.getSheet(0);  
        //得到列数  
        int colsNum = sheet.getColumns();  
        //得到行数  
        int rowsNum = sheet.getRows();  
        //单元格  
        Cell cell;  
        Map<Integer, String> map = new HashMap<Integer, String>();  
        for (int i = 1; i < rowsNum; i++) {//我的excel第一行是标题,所以 i从1开始  
            for (int j = 0; j < colsNum; j++) {  
                cell = sheet.getCell(j, i);//注意:第一个参数是列.第二个参数是行  
                String str=cell.getContents();
                System.out.println("i="+i+"j="+j+str);
                map.put(j, cell.getContents());  
            }  
        }  
        //做你需要的操作  
        System.out.println(map);  
    } catch (IOException e) {  
        e.printStackTrace();  
    } catch (BiffException e) {  
        e.printStackTrace();  
    }  
   // return null;
       /* boolean b = customerService.batchImport(name,file);
        if(b){
             String Msg ="批量导入EXCEL成功！";
             request.getSession().setAttribute("msg",Msg);    
        }else{
             String Msg ="批量导入EXCEL失败！";
             request.getSession().setAttribute("msg",Msg);
        } */
       return "add";
    }
	
}
