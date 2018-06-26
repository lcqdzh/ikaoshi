<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    <!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>在线考试平台</title>
  	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script src="https://cdn.bootcss.com/moment.js/2.18.1/moment-with-locales.min.js"></script>  
	<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">  
	<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>  
    

 <style type="text/css">
    /*
 * Style tweaks
 * --------------------------------------------------
 */
html,
body {
  overflow-x: hidden; /* Prevent scroll on narrow devices */
}
body {
  padding-top: 70px;
}
footer {
  padding: 30px 0;
}

/*
 * Off Canvas
 * --------------------------------------------------
 */
@media screen and (max-width: 767px) {
  .row-offcanvas {
    position: relative;
    -webkit-transition: all .25s ease-out;
         -o-transition: all .25s ease-out;
            transition: all .25s ease-out;
  }

  .row-offcanvas-right {
    right: 0;
  }

  .row-offcanvas-left {
    left: 0;
  }

  .row-offcanvas-right
  .sidebar-offcanvas {
    right: -50%; /* 6 columns */
  }

  .row-offcanvas-left
  .sidebar-offcanvas {
    left: -50%; /* 6 columns */
  }

  .row-offcanvas-right.active {
    right: 50%; /* 6 columns */
  }

  .row-offcanvas-left.active {
    left: 50%; /* 6 columns */
  }

  .sidebar-offcanvas {
    position: absolute;
    top: 0;
    width: 50%; /* 6 columns */
  }
}
    
    </style>
   
  </head>
<script> 
function enfocus()  
{  
    var dx_easys=document.getElementById("dx_easy").value;
    var dx_easy=dx_easys==""?0:Number(dx_easys);
    var dx_mediums=document.getElementById("dx_medium").value;
    var dx_medium=dx_mediums==""?0:Number(dx_mediums);
    var dx_hards=document.getElementById("dx_hard").value;
    var dx_hard=dx_hards==""?0:Number(dx_hards);
    var dx_scores=document.getElementById("dx_score").value;
    var dx_score=dx_scores==""?0:Number(dx_scores);

    var pd_easys=document.getElementById("pd_easy").value;
    var pd_easy=pd_easys==""?0:Number(pd_easys);
    var pd_mediums=document.getElementById("pd_medium").value;
    var pd_medium=pd_mediums==""?0:Number(pd_mediums);
    var pd_hards=document.getElementById("pd_hard").value;
    var pd_hard=pd_hards==""?0:Number(pd_hards);
    var pd_scores=document.getElementById("pd_score").value;
    var pd_score=pd_scores==""?0:Number(pd_scores);

    var dt_easys=document.getElementById("dt_easy").value;
    var dt_easy=dt_easys==""?0:Number(dt_easys);
    var dt_mediums=document.getElementById("dt_medium").value;
    var dt_medium=dt_mediums==""?0:Number(dt_mediums);
    var dt_hards=document.getElementById("dt_hard").value;
    var dt_hard=dt_hards==""?0:Number(dt_hards);
    var dt_scores=document.getElementById("dt_score").value;
    var dt_score=dt_scores==""?0:Number(dt_scores);
    document.getElementById("dx_num").value = dx_easy+dx_medium+dx_hard;  
    document.getElementById("pd_num").value = pd_easy+pd_medium+pd_hard;  
    document.getElementById("dt_num").value = dt_easy+dt_medium+dt_hard;  
    document.getElementById("dx_zscore").value = (dx_easy+dx_medium+dx_hard)*dx_score;  
    document.getElementById("pd_zscore").value = (pd_easy+pd_medium+pd_hard)*pd_score;  
    document.getElementById("dt_zscore").value =(dt_easy+dt_medium+dt_hard)*dt_score;  
    document.getElementById("score").value = (dt_easy+dt_medium+dt_hard)*dt_score+(pd_easy+pd_medium+pd_hard)*pd_score+(dx_easy+dx_medium+dx_hard)*dx_score;  
    console.log(dx_easy);
}
</script>
<script> 
  function check()  
  {  
      var scores=document.getElementById("score").value;
      var score=scores==""?0:Number(scores);
       console.log("hh");
      if(score==100)
      {

         alert("确认添加考试");  
        return true;
      }
      else{
        alert("试卷总分不是100");  
        return false;
      }
    
  }
  </script>    
<script type="text/javascript">
$(document).ready(function () {
	  $('[data-toggle="offcanvas"]').click(function () {
	    $('.row-offcanvas').toggleClass('active')
	  });
	});
</script>
<script>
$(function () {  

    var picker1 = $('#datetimepicker1').datetimepicker({  

        format: 'YYYY-MM-DD HH:mm:ss',  
        locale: moment.locale('zh-cn'),  
        //minDate: '2016-7-1'  
    });  
    var picker2 = $('#datetimepicker2').datetimepicker({  
        format: 'YYYY-MM-DD HH:mm:ss',  
        locale: moment.locale('zh-cn')  
    });  
    //动态设置最小值  
    picker1.on('dp.change', function (e) {  
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        picker1.data('DateTimePicker').minDate(currentdate);  
        picker2.data('DateTimePicker').minDate(e.date);  
    });  
    //动态设置最大值  
    picker2.on('dp.change', function (e) {  
        picker1.data('DateTimePicker').maxDate(e.date);  
    });  
});
</script>
  <body>
  <nav class="navbar navbar-fixed-top navbar-inverse">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">iTest</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="tea_home">首页</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="tea_info">修改密码</a></li>
            <li><a href="logout">注销</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div class="container">
    
      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="tea_kaoshiguanli" class="list-group-item" >考试管理</a>
            <a href="tea_chakankaoshi" class="list-group-item">查看考试</a>
            <a href="tea_kaoshinum" class="list-group-item">题库限制</a>
            <a href="tea_addkaoshi" class="list-group-item">添加考试</a>
            <a href="tea_add_kaoshi_s1" class="list-group-item active">添加考试(新版)</a>
          </div>
        </div><!--/.sidebar-offcanvas-->

		<div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1>欢迎使用在线考试系统</h1>
            <p>${error }</p>
          </div>
          <ul class="nav nav-tabs">
		  <li role="presentation"><a href="#">第一步</a></li>
		  <li role="presentation" class="active"><a href="#">第二步</a></li>
		  <li role="presentation"><a href="#">第三步</a></li>
		  <li role="presentation"><a href="#">第四步</a></li>
		</ul>
		<br>
			    <form action="tea_add_kaoshi_s2_f" method = "post" role="form">
							<div class="form-group">
                                 <!--  <label for="exampleInputEmail1">考试名称</label>-->
                                 <input class="form-control" placeholder="" id="exampleInputName1"  type="hidden" name = "test_name" value="${test_name}"  />
                            </div>   
                            <div class="form-group">
                                  <!--  <label for="exampleInputEmail1">题库名称及编号-->
							      </label><input class="form-control" placeholder="" id="exampleInputName1"   type="hidden" name = "tiku_IDname" value="${tiku_IDname}" />
                            </div>

                            <div class="row">  
						    <div class='col-sm-6'>  
						        <div class="form-group">  
						           <!--   <label>开始时间：</label>  -->
						            <!--指定 date标记-->  
						            <div class='input-group date' id='datetimepicker1'>  
						                <input type="hidden" class="form-control" name="begin_Time" value="${begin_timee}"/>  
						                 
						            </div>  
						        </div>  
						    </div>  
						    <div class='col-sm-6'>  
						        <div class="form-group">  
						          <!--   <label>结束时间：</label>  -->
						            <!--指定 date标记-->  
						            <div class='input-group date' id='datetimepicker2'>  
						                <input type="hidden" class="form-control" name="end_Time" value="${end_timee}"/>  
						                 
						            </div>  
						        </div>  
						    </div>  
						</div>  

                            <div class="form-group">
                               <!--    <label for="exampleInputEmail1">考试时长</label>-->
                                 <input class="form-control" placeholder=""  id="exampleInputzhuanye1" type="hidden" name = "time_long" value="${time_long}"/>
                            </div>
                            <!-- Table -->
			  <table class="table">
			   <thead>
			      <tr>
			         <th>题目类型</th>
			         <th>每题分值</th>
			         <th>简单个数</th>
			         <th>中等个数</th>
			         <th>困难个数</th>
			         <th>总个数</th>
			         <th>每题总分</th>
			         <th></th>
			         </tr>
			   </thead>
			   <tbody>
			      		<tr>
			      		<td>选择</td>
			      		<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dx_score" type = "number" name = "dx_score" /></td>	
			         		<td ><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dx_easy" type = "number" name = "dx_easy" />/${kaoshinum.dx_easy}个</td>
			         		<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dx_medium" type = "number" name = "dx_medium" /> /${kaoshinum.dx_medium}个</td>
			         		<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dx_hard" type = "number" name = "dx_hard" /> /${kaoshinum.dx_hard}个</td>
			      			<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dx_num" type = "number" name = "dx_num" /></td>
			      			<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dx_zscore" type = "number" name = "dx_zscore" /></td>
			      			
			      		</tr>
			      		<tr>
			      		<td>判断</td>
			      			<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="pd_score" type = "number" name = "pd_score" /></td>	
			      			<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="pd_easy" type = "number" name = "pd_easy" /> <nobr>/${kaoshinum.pd_medium}个<nobr></td>
			         		<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="pd_medium" type = "number" name = "pd_medium" /> <nobr>/${kaoshinum.pd_medium}个<nobr></td>
			         		<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="pd_hard" type = "number" name = "pd_hard" /> /${kaoshinum.pd_hard}个</td>
			      			<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="pd_num" type = "number" name = "pd_num" /></td>
			      			<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="pd_zscore" type = "number" name = "pd_zscore" /></td>
			      		</tr>
			      		<tr>
			      		<td>大题</td>
			      			<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dt_score" type = "number" name = "dt_score" /></td>	
			         		<td ><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dt_easy" type = "number" name = "dt_easy" />/${kaoshinum.dt_easy}个</td>
			         		<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dt_medium" type = "number" name = "dt_medium" /> <nobr>/${kaoshinum.dt_medium}个<nobr></td>
			         		<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dt_hard" type = "number" name = "dt_hard" /> /${kaoshinum.dt_hard}个</td>
			      			<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dt_num" type = "number" name = "dt_num" /></td>
			      			<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="enfocus(),value=value.replace(/[^\d]/g,'')" id="dt_zscore" type = "number" name = "dt_zscore" /></td>
			      		</tr>
			      		<td></td>
			         		<td ></td>
			         		<td></td>
			         		<td></td>
			      			<td>总分</td>
			      			<td><input style="width:60px;height:30px" class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="score" type = "text" name = "score" /></td>	
			      		</tr>
			   </tbody>
			</table>
                            <div class="row clearfix">
                                <div class="col-md-5 column">
                                    </div> </h1><input type = "submit" value = "提交" class="btn btn-primary btn-lg" onclick="return check();" ></a>
                                </div>
                                <div class="col-md-4 column">   
                                </div>
                                <div class="col-md-4 column">
                                </div>
                            </div>
                </form>
        </div><!--/.col-xs-12.col-sm-9-->

      </div><!--/row-->



    </div> <!-- /container -->
      <footer>
        <p>&copy; wyh&lcq</p>
      </footer>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
