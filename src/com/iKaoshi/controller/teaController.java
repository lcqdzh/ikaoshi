 package com.iKaoshi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iKaoshi.bean.Question;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.service.studentService;
import com.iKaoshi.service.teacherService;

@Controller
public class teaController {
	@RequestMapping("/tea_info")
	public ModelAndView stu_info(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("tea_info","error",str);
	}
	@RequestMapping("/tea_login")
	public ModelAndView stu_login(HttpServletRequest request)
	{
		String idd = request.getParameter("username");
		int id=Integer.parseInt(idd);
		request.getSession().setAttribute("sessiontea_id",id);     //用Session保存用户名  
		
        String password = request.getParameter("password");
        System.out.println(id);System.out.println(password);
        String str="123";
        if(teacherService.login(id, password))
        {
        	return new ModelAndView("tea_home","error",str);
        }
        return new ModelAndView("tea_info","error",str);
	}
	@RequestMapping("/tea_home")
	public ModelAndView stu_home(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("tea_home","error",str);
	}
	
	//跳转到题库管理界面
	//create by lcq 2018年6月15日19:14:55
	@RequestMapping("/tea_tikuguanli")
	public ModelAndView tea_tikuguanli(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("tea_tikuguanli","error",str);
	}
	
	//跳转到添加题库界面
	//create by lcq 2018年6月15日20:03:13
	@RequestMapping("/tea_addtiku")
	public ModelAndView tea_addtiku(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("tea_addtiku","error",str);
	}
	//在添加题库界面中点击添加按钮
	//create by lcq 2018年6月15日20:13:19
	@RequestMapping("/tea_addtiku_f")
	public ModelAndView tea_addtiku_f(HttpServletRequest request)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		System.out.println(tea_id);
		String tiku_name = request.getParameter("tiku_name");
		System.out.println(tiku_name);
		String str="123";
		Tikuxinxi t=new Tikuxinxi();
		t.setTea_Id(tea_id);t.setTiku_name(tiku_name);
		teacherService.addTikuxinxi(t);
	    return new ModelAndView("tea_addtiku","error",str);
	}
	//跳转到查看题库界面
	//create by lcq 2018年6月16日08:37:47
	@RequestMapping("/tea_chakantiku")
	public ModelAndView tea_chakantiku(HttpServletRequest request)
	{
		String str="123";
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<Tikuxinxi> t=null;
		t=teacherService.quary(tea_id);
		
        return new ModelAndView("tea_chakantiku","tikuxinxi",t);
	}
	
	 //具体题库管理 对指定的题库进行管理
	// create by lcq 2018年6月16日19:40:09
	@RequestMapping("/tea_dangetikuguanli")
	public ModelAndView tea_dangetikuguanli(HttpServletRequest request,Model model)
	{
		
		String tiku_ID = request.getParameter("tiku_ID");
		//String tiku_name = request.getParameter("tiku_name");
		//model.addAttribute("tiku_name", tiku_name);
		int tiku_Id=Integer.parseInt(tiku_ID);
		List<Question> q=null;
		q=teacherService.quaryQuestionbytikuID(tiku_Id);
		System.out.println(q.size());
		model.addAttribute("question", q);
        return new ModelAndView("tea_dangetikuguanli","tiku_ID",tiku_ID);
	}
	//题目信息修改界面
	//create by lcq 2018年6月17日09:53:08
	@RequestMapping("/tea_changequestion")
	public ModelAndView tea_changequestion(HttpServletRequest request,Model model)
	{
		
		String question_Idd = request.getParameter("question_Id");
		String tiku_Idd = request.getParameter("tiku_ID");
		//String tiku_name = request.getParameter("tiku_name");
		//model.addAttribute("tiku_name", tiku_name);
		int question_Id=Integer.parseInt(question_Idd);
		int tiku_Id=Integer.parseInt(tiku_Idd);
		List<Question> q=null;
		q=teacherService.quaryQuestionbyquestionID(question_Id, tiku_Id);
		System.out.println(q.size());
		if(q.size()!=0) 
		{
			System.out.println(q.get(0).getQuestion_type());
			Question nq=new Question();
			nq=q.get(0);
			model.addAttribute("question", nq);
		}
		else
		{
			Question nq=new Question();
			model.addAttribute("question",nq );
		}
        return new ModelAndView("tea_changequestion","tiku_ID",tiku_Id);
	}
	//具体修改题目信息的代码  选择
	//create by lcq 2018年6月17日10:38:17
	@RequestMapping("/tea_changequestion_f1")
	public ModelAndView tea_changequestion_f1(HttpServletRequest request,Model model)
	{
		
		
		String tiku_Idd = request.getParameter("tiku_ID");
		String question_Idd= request.getParameter("question_Id");
		int question_Id=Integer.parseInt(question_Idd);
		int tiku_Id=Integer.parseInt(tiku_Idd);
        String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String choice_A=request.getParameter("choice_A");System.out.println(choice_A);
		String choice_B=request.getParameter("choice_B");System.out.println(choice_B);
		String choice_C=request.getParameter("choice_C");System.out.println(choice_C);
		String choice_D=request.getParameter("choice_D");System.out.println(choice_D);
		String answerr=request.getParameter("answer");
		int answer=0;
		System.out.println(answerr);
		if(answerr.equals("A")) {
			answer=1;
		}else if(answerr.equals("B")) {
			answer=2;
		}else if(answerr.equals("C")) {
				answer=3;
		}else if(answerr.equals("D")) {
				answer=4;
		}
		System.out.println(answer);
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		List<Question> q=null;
		q=teacherService.quaryQuestionbyquestionID(question_Id, tiku_Id);
		System.out.println(q.size());
		Question nq=new Question();
		if(q.size()!=0) 
		{
			System.out.println(q.get(0).getQuestion_type());
			nq=q.get(0);
		}
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(!choice_A.isEmpty()) nq.setChoice_A(choice_A);
		if(!choice_B.isEmpty()) nq.setChoice_B(choice_B);
		if(!choice_C.isEmpty()) nq.setChoice_C(choice_C);
		if(!choice_D.isEmpty()) nq.setChoice_D(choice_D);
		if(answer!=0) nq.setAnswer(answer);
		if(lable!=0) nq.setLable(lable);
		System.out.println(nq.toString());
		teacherService.updatebyone(nq);
		model.addAttribute("question", nq);
        return new ModelAndView("tea_changequestion","tiku_ID",tiku_Idd);
	}
	//修改判断题
	//create by lcq 2018年6月17日14:44:15
	@RequestMapping("/tea_changequestion_f2")
	public ModelAndView tea_changequestion_f2(HttpServletRequest request,Model model)
	{
		
		
		String tiku_Idd = request.getParameter("tiku_ID");
		String question_Idd= request.getParameter("question_Id");
		int question_Id=Integer.parseInt(question_Idd);
		int tiku_Id=Integer.parseInt(tiku_Idd);
        String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String answerr=request.getParameter("answer");
		int answer=0;
		if(answerr.equals("正确")) {
			answer=1;
		}else if(answerr.equals("错误")) {
			answer=2;
		}
		System.out.println(answer);
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		List<Question> q=null;
		q=teacherService.quaryQuestionbyquestionID(question_Id, tiku_Id);
		System.out.println(q.size());
		Question nq=new Question();
		if(q.size()!=0) 
		{
			System.out.println(q.get(0).getQuestion_type());
			nq=q.get(0);
		}
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(answer!=0) nq.setAnswer(answer);
		if(lable!=0) nq.setLable(lable);
		System.out.println(nq.toString());
		teacherService.updatebyone(nq);
		model.addAttribute("question", nq);
		
        return new ModelAndView("tea_changequestion","tiku_ID",tiku_Idd);
	}
	//修改大题
	//create by lcq 2018年6月17日14:46:12
	@RequestMapping("/tea_changequestion_f3")
	public ModelAndView tea_changequestion_f3(HttpServletRequest request,Model model)
	{
		
		
		String tiku_Idd = request.getParameter("tiku_ID");
		String question_Idd= request.getParameter("question_Id");
		int question_Id=Integer.parseInt(question_Idd);
		int tiku_Id=Integer.parseInt(tiku_Idd);
        String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		List<Question> q=null;
		q=teacherService.quaryQuestionbyquestionID(question_Id, tiku_Id);
		System.out.println(q.size());
		Question nq=new Question();
		if(q.size()!=0) 
		{
			System.out.println(q.get(0).getQuestion_type());
			nq=q.get(0);
		}
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(lable!=0) nq.setLable(lable);
		System.out.println(nq.toString());
		teacherService.updatebyone(nq);
		model.addAttribute("question", nq);
        return new ModelAndView("tea_changequestion","tiku_ID",tiku_Idd);
	}
	//添加选择界面
	//create by lcq 2018年6月17日15:53:33
	@RequestMapping("/tea_addxuanze")
	public ModelAndView tea_addxuanze(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int count=teacherService.getMaxquestion(tiku_Id);
		model.addAttribute("question_Id", count+1);
        return new ModelAndView("tea_addxuanze","tiku_ID",tiku_Id);
	}
	//添加判断界面
	//create by lcq 2018年6月17日15:53:33
	@RequestMapping("/tea_addpanduan")
	public ModelAndView tea_addpanduan(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int count=teacherService.getMaxquestion(tiku_Id);
		model.addAttribute("question_Id", count+1);
        return new ModelAndView("tea_addpanduan","tiku_ID",tiku_Id);
	}
	//添加大题界面
	//create by lcq 2018年6月17日15:53:33
	@RequestMapping("/tea_adddati")
	public ModelAndView tea_adddati(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int count=teacherService.getMaxquestion(tiku_Id);
		model.addAttribute("question_Id", count+1);
        return new ModelAndView("tea_adddati","tiku_ID",tiku_Id);
	}
	//添加选择功能
	//create by lcq 2018年6月17日16:24:58
	@RequestMapping("/tea_addxuanze_f")
	public ModelAndView tea_addxuanze_f(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int question_Id=teacherService.getMaxquestion(tiku_Id)+1;
		String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String choice_A=request.getParameter("choice_A");System.out.println(choice_A);
		String choice_B=request.getParameter("choice_B");System.out.println(choice_B);
		String choice_C=request.getParameter("choice_C");System.out.println(choice_C);
		String choice_D=request.getParameter("choice_D");System.out.println(choice_D);
		String answerr=request.getParameter("answer");
		int answer=0;
		System.out.println(answerr);
		if(answerr.equals("A")) {
			answer=1;
		}else if(answerr.equals("B")) {
			answer=2;
		}else if(answerr.equals("C")) {
				answer=3;
		}else if(answerr.equals("D")) {
				answer=4;
		}
		System.out.println(answer);
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		Question nq=new Question();
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(!choice_A.isEmpty()) nq.setChoice_A(choice_A);
		if(!choice_B.isEmpty()) nq.setChoice_B(choice_B);
		if(!choice_C.isEmpty()) nq.setChoice_C(choice_C);
		if(!choice_D.isEmpty()) nq.setChoice_D(choice_D);
		if(answer!=0) nq.setAnswer(answer);
		if(lable!=0) nq.setLable(lable);
		nq.setQuestion_type(1);
		System.out.println(nq.toString());
		teacherService.addquestion(nq);
        return new ModelAndView("tea_addxuanze","tiku_ID",tiku_Id);
	}
	//添加判断功能
	//create by lcq 2018年6月17日16:25:03
	@RequestMapping("/tea_addpanduan_f")
	public ModelAndView tea_addpanduan_f(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int question_Id=teacherService.getMaxquestion(tiku_Id)+1;
		String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String answerr=request.getParameter("answer");
		int answer=0;
		System.out.println(answerr);
		if(answerr.equals("正确")) {
			answer=1;
		}else if(answerr.equals("错误")) {
			answer=2;
		}
		System.out.println(answer);
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		Question nq=new Question();
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(answer!=0) nq.setAnswer(answer);
		if(lable!=0) nq.setLable(lable);
		nq.setQuestion_type(2);
		System.out.println(nq.toString());
		teacherService.addquestion(nq);
        return new ModelAndView("tea_addpanduan","tiku_ID",tiku_Id);
	}
	//添加大题功能
	//create by lcq 2018年6月17日16:25:07
	@RequestMapping("/tea_adddati_f")
	public ModelAndView tea_adddati_f(HttpServletRequest request,Model model)
	{
		String tiku_Idd = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_Idd);
		int question_Id=teacherService.getMaxquestion(tiku_Id)+1;
		String question_content=request.getParameter("question_content");//.isEmpty()?1:Integer.parseInt(request.getParameter("tel"));
		String lablee=request.getParameter("lable");
		int lable=0;
		if(lablee.equals("1")) {
			lable=1;
		}else if(lablee.equals("2")) {
			lable=2;
		}else if(lablee.equals("3")) {
				lable=3;
		}
		System.out.println(lable);
		Question nq=new Question();
		nq.setTiku_Id(tiku_Id);
		nq.setQuestion_Id(question_Id);
		if(!question_content.isEmpty()) nq.setQuestion_content(question_content);
		if(lable!=0) nq.setLable(lable);
		nq.setQuestion_type(3);
		System.out.println(nq.toString());
		teacherService.addquestion(nq);
        return new ModelAndView("tea_adddati","tiku_ID",tiku_Id);
	}
	//根据题库号 题号删除某一试题
	@RequestMapping("/tea_delquestion")
	public ModelAndView tea_delquestion_f(HttpServletRequest request,Model model)
	{
		String tiku_ID = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_ID);
		String question_Idd= request.getParameter("question_Id");
		int question_Id=Integer.parseInt(question_Idd);
		teacherService.delquestion(question_Id, tiku_Id);
		
		
		
		//显示相关的代码
		List<Question> q=null;
		q=teacherService.quaryQuestionbytikuID(tiku_Id);
		System.out.println(q.size());
		model.addAttribute("question", q);
    	return new ModelAndView("tea_dangetikuguanli","tiku_ID",tiku_Id);
	}
}
