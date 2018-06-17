package com.iKaoshi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.iKaoshi.service.*;
@Controller
public class testController {
	@RequestMapping("/stu_info")
	public ModelAndView stu_info(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("stu_info","error",str);
	}
	@RequestMapping("/stu_login")
	public ModelAndView stu_login(HttpServletRequest request)
	{
		System.out.println("123");
		String idd = request.getParameter("username");
		int id=Integer.parseInt(idd);
        String password = request.getParameter("password");
        System.out.println(id);System.out.println(password);
        String str="123";
        if(studentService.login(id, password))
        {
        	return new ModelAndView("stu_home","error",str);
        }
        return new ModelAndView("stu_info","error",str);
	}
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("add","error",str);
	}
	@RequestMapping("/stu_home")
	public ModelAndView stu_home(HttpServletRequest request)
	{
		String str="123";
        return new ModelAndView("stu_home","error",str);
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
