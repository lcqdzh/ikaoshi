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
            <a href="tea_addkaoshi" class="list-group-item active">添加考试</a>
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
			    <form action="tea_addkaoshi_f" method = "post" role="form">
							<div class="form-group">
                                 <label for="exampleInputEmail1">考试名称</label><input class="form-control" placeholder="" id="exampleInputName1" type = "text" name = "test_name" />
                            </div>   
                            <div class="form-group">
                                  <label for="exampleInputEmail1">题库名称及编号
							      </label>
                                  <select class="form-control" id="exampleInputyuanxi1" type = "text" name = "tiku_IDname">
							      <c:if test="${!empty tikuxinxi}"> 
               							<c:forEach items="${tikuxinxi}" var="u">
							      				<option>${u.tiku_ID}:${u.tiku_name}</option>
							      		</c:forEach>
							      </c:if>
							    </select>
                            </div>

                            <div class="row">  
						    <div class='col-sm-6'>  
						        <div class="form-group">  
						            <label>开始时间：</label>  
						            <!--指定 date标记-->  
						            <div class='input-group date' id='datetimepicker1'>  
						                <input type='text' class="form-control" name="begin_Time"/>  
						                <span class="input-group-addon">  
						                    <span class="glyphicon glyphicon-calendar"></span>  
						                </span>  
						            </div>  
						        </div>  
						    </div>  
						    <div class='col-sm-6'>  
						        <div class="form-group">  
						            <label>结束时间：</label>  
						            <!--指定 date标记-->  
						            <div class='input-group date' id='datetimepicker2'>  
						                <input type='text' class="form-control" name="end_Time"/>  
						                <span class="input-group-addon">  
						                    <span class="glyphicon glyphicon-calendar"></span>  
						                </span>  
						            </div>  
						        </div>  
						    </div>  
						</div>  

                            <div class="form-group">
                                 <label for="exampleInputEmail1">考试时长</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "time_long" />
                            </div>
                             <div class="form-group">
                                 <label for="exampleInputEmail1">选择分值</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "dx_score" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">选择简单题个数</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "dx_easy" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">选择中等题个数</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "dx_medium" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">选择困难题个数</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "dx_hard" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">判断分值</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "pd_score" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">判断简单题个数</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "pd_easy" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">判断中等题个数</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "pd_medium" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">判断困难题个数</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "pd_hard" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">大题分值</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "dt_score" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">大题简单题个数</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "dt_easy" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">大题中等题个数</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "dt_medium" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">大题困难题个数</label><input class="form-control" placeholder="" onkeyup="value=value.replace(/[^\d]/g,'')" id="exampleInputzhuanye1" type = "text" name = "dt_hard" />
                            </div>                        
                            <div class="row clearfix">
                                <div class="col-md-5 column">
                                    </div> </h1><input type = "submit" value = "提交" class="btn btn-primary btn-lg"  ></a>
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
