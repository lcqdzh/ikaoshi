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
            <a href="tea_kaoshinum" class="list-group-item active">题库限制</a>
            <a href="tea_addkaoshi" class="list-group-item">添加考试</a>
            <a href="tea_add_kaoshi_s1" class="list-group-item">添加考试(新版)</a>
          </div>
        </div><!--/.sidebar-offcanvas-->

		<div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1>欢迎使用在线考试系统</h1>
            <p>下面是题库信息</p>
          </div>
			    <div class="panel panel-default">
			  <!-- Default panel contents -->
			  <div class="panel-heading">考试信息</div>
			  <!-- Table -->
			  <table class="table">
			   <thead>
			      <tr>
			         <th>题库编号</th>
			         <th>题库名称</th>
			         <th></th>
			         </tr>
			   </thead>
			   <tbody>
			   <c:if test="${!empty kaoshi}"> 
               		<c:forEach items="${kaoshi}" var="u">
			      		<tr>
			         		<td>${u.tiku_Id}</td>
			         		<td>${u.tiku_name}</td>
			         		<td></td>
			      		</tr>
			      		<tr>
			         		<td>选择简单题：${u.dx_easy}个</td>
			         		<td>选择中等题：${u.dx_medium}个</td>
			         		<td>选择困难题：${u.dx_hard}个</td>
			      		</tr>
			      		<tr>
			         		<td>判断简单题：${u.pd_easy}个</td>
			         		<td>判断中等题：${u.pd_medium}个</td>
			         		<td>判断困难题：${u.pd_hard}个</td>
			      		</tr>
			      		<tr>
			         		<td>大题简单题：${u.dt_easy}个</td>
			         		<td>答题中等题：${u.dt_medium}个</td>
			         		<td>大题困难题：${u.dt_hard}个</td>
			      		</tr>
			      	</c:forEach>
              </c:if>
			   </tbody>
			</table>
			</div>
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
