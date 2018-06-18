 package com.iKaoshi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iKaoshi.bean.Question;
import com.iKaoshi.bean.TeaTestInfo;
import com.iKaoshi.bean.Tikuxinxi;
import com.iKaoshi.service.studentService;
import com.iKaoshi.service.teacherService;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

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
	//create by lcq 2018年6月17日19:16:43
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
	//跳转到导入题库界面
	//create by lcq 2018年6月17日19:24:00
	@RequestMapping("/tea_daorutiku")
	public ModelAndView tea_daorutiku(HttpServletRequest request,Model model)
	{
		String tiku_ID = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_ID);
		return new ModelAndView("tea_daorutiku","tiku_ID",tiku_Id);
	}
	@RequestMapping(value = "/tea_daorutiku_f", method = RequestMethod.POST)
    public ModelAndView batchimport(@RequestParam(value="filename") MultipartFile file,
            HttpServletRequest request,HttpServletResponse response) throws IOException{
        
		String tiku_ID = request.getParameter("tiku_ID");
		int tiku_Id=Integer.parseInt(tiku_ID);
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
        		
        		Question q=new Question();
                
                cell = sheet.getCell(0, i);//get question_conten  
                String str=cell.getContents();
                q.setQuestion_content(str);
                
                cell = sheet.getCell(1, i);//get_type
                str=cell.getContents();
                int question_type=0;
                if(str.equals("1")) {
                	question_type=1;
                }else if(str.equals("2")) {
                	question_type=2;
                }
                else if(str.equals("3")) {
                	question_type=3;
                }
                q.setQuestion_type(question_type);
                
                cell = sheet.getCell(2, i);//get choice_A
                str=cell.getContents();
                q.setChoice_A(str);
                
                cell = sheet.getCell(3, i);//get choice_B
                str=cell.getContents();
                q.setChoice_B(str);
                
                cell = sheet.getCell(4, i);//get choice_C
                str=cell.getContents();
                q.setChoice_C(str);
                
                cell = sheet.getCell(5, i);//get choice_D
                str=cell.getContents();
                q.setChoice_D(str);
                
                cell = sheet.getCell(6, i);//get answer
                str=cell.getContents();
                System.out.println("answer"+str);
                if(str.equals("A")) {
                	q.setAnswer(1);
                }else if(str.equals("B")) {
                	q.setAnswer(2);
                }else if(str.equals("C")) {
                	q.setAnswer(3);
                }else if(str.equals("D")) {
                	q.setAnswer(4);
                }
                
                cell = sheet.getCell(7, i);//get question_level
                str=cell.getContents();
                int lable=0;
                if(str.equals("1")) {
                	lable=1;
                }else if(str.equals("2")) {
                	lable=2;
                }
                else if(str.equals("3")) {
                	lable=3;
                }
                q.setLable(lable);
                q.setTiku_Id(tiku_Id);
                q.setQuestion_Id(teacherService.getMaxquestion(tiku_Id)+1);
                System.out.println(q.toString());
                
                //teacherService.addquestion(q);
        }  
        //做你需要的操作  
        System.out.println(map);  
    } catch (IOException e) {  
        e.printStackTrace();  
    } catch (BiffException e) {  
        e.printStackTrace();  
    }  

       return new ModelAndView("tea_daorutiku","tiku_ID",tiku_Id);
    }
	//跳转到考试管理界面
	//create by lcq 2018年6月17日23:41:38
	@RequestMapping("/tea_kaoshiguanli")
	public ModelAndView tea_kaoshiguanli(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");

		return new ModelAndView("tea_kaoshiguanli","tea_id",tea_id);
	}
	//跳转到查看考试界面
	//create by lcq 2018年6月17日23:52:38
	@RequestMapping("/tea_chakankaoshi")
	public ModelAndView tea_chakankaoshi(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<TeaTestInfo> t=null;
		t=teacherService.quaryTestinfobyteaid(tea_id);
		model.addAttribute("teatestinfo",t );
		return new ModelAndView("tea_chakankaoshi","tea_id",tea_id);
	}
	//跳转到添加考试界面
	//create by lcq 2018年6月17日23:54:09
	@RequestMapping("/tea_addkaoshi")
	public ModelAndView tea_addkaoshi(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		List<Tikuxinxi> tikuxinxi=null;
		System.out.println("tea_id:"+tea_id);
		tikuxinxi=teacherService.quary(tea_id);
		model.addAttribute("tikuxinxi",tikuxinxi);
		return new ModelAndView("tea_addkaoshi","tea_id",tea_id);
	}
	//获取教师添加考试的相关信息
	//create by lcq 2018年6月18日10:11:52
	@RequestMapping("/tea_addkaoshi_f")
	public ModelAndView tea_addkaoshi_f(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_name = request.getParameter("test_name");
		String tiku_IDname = request.getParameter("tiku_IDname");String tiku_Idd=tiku_IDname.substring(0, tiku_IDname.indexOf(':'));int tiku_id=tiku_Idd.isEmpty()?0:Integer.parseInt(tiku_Idd);
		String begin_timee = request.getParameter("begin_Time");//Timestamp begin_time= new Timestamp(System.currentTimeMillis());begin_time=Timestamp.valueOf("begin_timee");
		
		Date d1 = null;
		try {
			d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin_timee);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp begin_time= new Timestamp(d1.getTime());
		String end_timee = request.getParameter("end_Time");//Timestamp end_time= new Timestamp(System.currentTimeMillis());end_time.valueOf("end_timee");
		Date d2 = null;
		try {
			d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin_timee);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp end_time= new Timestamp(d2.getTime());
		String time_longg = request.getParameter("time_long");int time_long=time_longg.isEmpty()?0:Integer.parseInt(time_longg);
		String dx_scoree = request.getParameter("dx_score");int dx_score=dx_scoree.isEmpty()?0:Integer.parseInt(dx_scoree);
		String dx_easyy = request.getParameter("dx_easy");int dx_easy=dx_easyy.isEmpty()?0:Integer.parseInt(dx_easyy);
		String dx_mediumm = request.getParameter("dx_medium");int dx_medium=dx_mediumm.isEmpty()?0:Integer.parseInt(dx_mediumm);
		String dx_hardd = request.getParameter("dx_hard");int dx_hard=dx_hardd.isEmpty()?0:Integer.parseInt(dx_hardd);
		String pd_scoree = request.getParameter("pd_score");int pd_score=pd_scoree.isEmpty()?0:Integer.parseInt(pd_scoree);
		String pd_easyy = request.getParameter("pd_easy");int pd_easy=pd_easyy.isEmpty()?0:Integer.parseInt(pd_easyy);
		String pd_mediumm = request.getParameter("pd_medium");int pd_medium=pd_mediumm.isEmpty()?0:Integer.parseInt(pd_mediumm);
		String pd_hardd = request.getParameter("pd_hard");int pd_hard=pd_hardd.isEmpty()?0:Integer.parseInt(pd_hardd);
		String dt_scoree = request.getParameter("dt_score");int dt_score=dt_scoree.isEmpty()?0:Integer.parseInt(dt_scoree);
		String dt_easyy = request.getParameter("dt_easy");int dt_easy=dt_easyy.isEmpty()?0:Integer.parseInt(dt_easyy);
		String dt_mediumm = request.getParameter("dt_medium");int dt_medium=dt_mediumm.isEmpty()?0:Integer.parseInt(dt_mediumm);
		String dt_hardd = request.getParameter("dt_hard");int dt_hard=dt_hardd.isEmpty()?0:Integer.parseInt(dt_hardd);
		//应该先判断是否满足添加的条件
		int test_id=teacherService.getMaxtestid()+1;
		TeaTestInfo t=new TeaTestInfo();
		t.setTea_id(tea_id);t.setTest_id(test_id);t.setTest_name(test_name);t.setTiku_id(tiku_id);
		t.setBegin_time(begin_time);t.setEnd_time(end_time);t.setTime_long(time_long);
		t.setDx_easy(dx_easy);t.setDx_medium(dx_medium);t.setDx_hard(dx_hard);t.setDx_score(dx_score);
		t.setPd_easy(pd_easy);t.setPd_medium(pd_medium);t.setPd_hard(pd_hard);t.setPd_score(pd_score);
		t.setDt_easy(dt_easy);t.setDt_medium(dt_medium);t.setDt_hard(dt_hard);t.setDt_score(dt_score);
		System.out.println(t.toString());
		teacherService.addteatestinfo(t);
		/*
		System.out.println("test_name=" + test_name+
				" tiku_IDname=" + tiku_IDname+
				" begin_time=" + begin_time+
				" end_time=" + end_time+
				" time_long=" + time_long+
				" dx_score=" + dx_score+
				" dx_easy=" + dx_easy+
				" dx_medium=" + dx_medium+
				" dx_hard=" + dx_hard+
				" pd_score=" + pd_score+
				" pd_easy=" + pd_easy+
				" pd_medium=" + pd_medium+
				" pd_hard=" + pd_hard+
				" dt_score=" + dt_score+
				" dt_easy=" + dt_easy+
				" dt_medium=" + dt_medium+
				" dt_hard="+dt_hard);
				*/
		return new ModelAndView("tea_addkaoshi","tea_id",tea_id);
	}
	//跳转到单个考试信息修改界面
	//create by lcq 2018年6月18日19:13:28
	@RequestMapping("/tea_dangekaoshiguanli")
	public ModelAndView tea_dangekaoshiguanli(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		List<TeaTestInfo> t=null;
		System.out.println(test_id);
		t=teacherService.quaryTestinfobytestid(test_id);
		TeaTestInfo nt=new TeaTestInfo();
		System.out.println("size="+t.size());
		if(t.size()!=0)
		{
			System.out.println(t.get(0));
			nt=t.get(0);
		}
		System.out.println(nt.toString());
		model.addAttribute("teatestinfo", nt);
		
		
		
		List<Tikuxinxi> tikuxinxi=null;
		System.out.println("tea_id:"+tea_id);
		tikuxinxi=teacherService.quary(tea_id);
		model.addAttribute("tikuxinxi",tikuxinxi);
		return new ModelAndView("tea_dangekaoshiguanli","tea_id",tea_id);
	}
	//修改具体的某一个试题的动作
	//create by lcq 2018年6月18日21:09:00 tea_dangekaoshiguanli_f
	@RequestMapping("/tea_dangekaoshiguanli_f")
	public ModelAndView tea_dangekaoshiguanli_f(HttpServletRequest request,Model model)
	{
		int tea_id=(int)request.getSession().getAttribute("sessiontea_id");
		String test_idd = request.getParameter("test_id");
		int test_id=test_idd.isEmpty()?0:Integer.parseInt(test_idd);
		List<TeaTestInfo> t=null;
		System.out.println(test_id);
		t=teacherService.quaryTestinfobytestid(test_id);
		TeaTestInfo nt=new TeaTestInfo();
		System.out.println("size="+t.size());
		if(t.size()!=0)
		{
			System.out.println(t.get(0));
			nt=t.get(0);
		}
		System.out.println(nt.toString());

		String test_name = request.getParameter("test_name");
		String tiku_IDname = request.getParameter("tiku_IDname");String tiku_Idd=tiku_IDname.substring(0, tiku_IDname.indexOf(':'));int tiku_id=tiku_Idd.isEmpty()?0:Integer.parseInt(tiku_Idd);
		String begin_timee = request.getParameter("begin_Time");//Timestamp begin_time= new Timestamp(System.currentTimeMillis());begin_time=Timestamp.valueOf("begin_timee");
		System.out.println("1");
		Date d1 = null;
		System.out.println("2"+begin_timee);
		if(!begin_timee.isEmpty()) {
			try {
				d1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin_timee);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timestamp begin_time= new Timestamp(d1.getTime());
			nt.setBegin_time(begin_time);
		}
		
		String end_timee = request.getParameter("end_Time");//Timestamp end_time= new Timestamp(System.currentTimeMillis());end_time.valueOf("end_timee");
		Date d2 = null;
		if(!end_timee.isEmpty()) {
			try {
				d2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin_timee);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Timestamp end_time= new Timestamp(d2.getTime());
			nt.setEnd_time(end_time);
		}
		
		String time_longg = request.getParameter("time_long");int time_long=time_longg.isEmpty()?0:Integer.parseInt(time_longg);
		String dx_scoree = request.getParameter("dx_score");int dx_score=dx_scoree.isEmpty()?0:Integer.parseInt(dx_scoree);
		String dx_easyy = request.getParameter("dx_easy");int dx_easy=dx_easyy.isEmpty()?0:Integer.parseInt(dx_easyy);
		String dx_mediumm = request.getParameter("dx_medium");int dx_medium=dx_mediumm.isEmpty()?0:Integer.parseInt(dx_mediumm);
		String dx_hardd = request.getParameter("dx_hard");int dx_hard=dx_hardd.isEmpty()?0:Integer.parseInt(dx_hardd);
		String pd_scoree = request.getParameter("pd_score");int pd_score=pd_scoree.isEmpty()?0:Integer.parseInt(pd_scoree);
		String pd_easyy = request.getParameter("pd_easy");int pd_easy=pd_easyy.isEmpty()?0:Integer.parseInt(pd_easyy);
		String pd_mediumm = request.getParameter("pd_medium");int pd_medium=pd_mediumm.isEmpty()?0:Integer.parseInt(pd_mediumm);
		String pd_hardd = request.getParameter("pd_hard");int pd_hard=pd_hardd.isEmpty()?0:Integer.parseInt(pd_hardd);
		String dt_scoree = request.getParameter("dt_score");int dt_score=dt_scoree.isEmpty()?0:Integer.parseInt(dt_scoree);
		String dt_easyy = request.getParameter("dt_easy");int dt_easy=dt_easyy.isEmpty()?0:Integer.parseInt(dt_easyy);
		String dt_mediumm = request.getParameter("dt_medium");int dt_medium=dt_mediumm.isEmpty()?0:Integer.parseInt(dt_mediumm);
		String dt_hardd = request.getParameter("dt_hard");int dt_hard=dt_hardd.isEmpty()?0:Integer.parseInt(dt_hardd);
		System.out.println("dx_hard="+dx_hard);
		if(!test_name.isEmpty()) nt.setTest_name(test_name);
		if(tiku_id!=nt.getTiku_id()) nt.setTiku_id(tiku_id);
		//if(!begin_timee.isEmpty()) nt.setBegin_time(begin_time);
		//if(!end_timee.isEmpty()) nt.setEnd_time(end_time);
		if(!time_longg.isEmpty()) nt.setTime_long(time_long);
		if(!dx_scoree.isEmpty()) nt.setDx_score(dx_score);
		if(!dx_easyy.isEmpty()) nt.setDx_easy(dx_easy);
		if(!dx_mediumm.isEmpty()) nt.setDx_medium(dx_medium);
		if(!dx_hardd.isEmpty()) nt.setDx_hard(dx_hard);
		if(!pd_scoree.isEmpty()) nt.setPd_score(pd_score);
		if(!pd_easyy.isEmpty()) nt.setPd_easy(pd_easy);
		if(!pd_mediumm.isEmpty()) nt.setPd_medium(pd_medium);
		if(!pd_hardd.isEmpty()) nt.setPd_hard(pd_hard);		
		if(!dt_scoree.isEmpty()) nt.setDt_score(dt_score);
		if(!dt_easyy.isEmpty()) nt.setDt_easy(dt_easy);
		if(!dt_mediumm.isEmpty()) nt.setDt_medium(dt_medium);
		if(!dt_hardd.isEmpty()) nt.setDt_hard(dt_hard);		
		System.out.println(nt);
		
		
		
		teacherService.updateteatestinfobyone(nt);
		model.addAttribute("teatestinfo", nt);
		
		
		
		List<Tikuxinxi> tikuxinxi=null;
		System.out.println("tea_id:"+tea_id);
		tikuxinxi=teacherService.quary(tea_id);
		model.addAttribute("tikuxinxi",tikuxinxi);
		return new ModelAndView("tea_dangekaoshiguanli","tea_id",tea_id);
	}
	//用来测试
	
	@RequestMapping("/ttt")
	public String ttt(HttpServletRequest request)
	{
		teacherService.getkaoshi(10, 2);
		return "hellow";
	}
}
