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

  <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	
	<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <!-- Custom styles for this template -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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
            <a href="tea_chengji_kaoshilist" class="list-group-item active">考试信息</a>
          </div>
        </div><!--/.sidebar-offcanvas-->


        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1>欢迎使用在线考试系统</h1>
            <p>下面显示成绩分析</p>
          </div>
		<div class="row">
		<ul class="nav nav-tabs">
		  <li role="presentation"><a href="tea_chengji_testid?test_id=${test_id }">成绩</a></li>
		  <li role="presentation" class="active"><a href="tea_chengji_fenxi?test_id=${test_id }">分析</a></li>
		</ul>
			<div class="panel panel-default">
			  <!-- Default panel contents -->
			  <div class="panel-heading">成绩分析结果</div>
			  <div class="panel-body">
			    <p></p>
			  </div>
			
			<table class="table table-hover">
			   <thead>
			      <tr>
			         <th>项目</th>
			         <th>数据</th>
			         <th>项目</th>
			         <th>数据</th>
			      </tr>
			   </thead>
			   <tbody>
	      		<tr>
	         		<td>最高分:</td>
	         		<td>${cjfx.maxscore}</td>
	         		<td>最低分:</td>
	         		<td>${cjfx.minscore}</td>
	      		</tr>
	      		<tr>
	         		<td>平均分:</td>
	         		<td>${cjfx.avgscore }</td>
	         		<td></td>
	         		<td></td>
	      		</tr>
	      		<tr>
	         		<td>总人数:</td>
	         		<td>${cjfx.num}</td>
	         		<td></td>
	         		<td></td>
	      		</tr>
	      		<tr>
	         		<td>已经批改完的人数:</td>
	         		<td>${cjfx.nnum }</td>
	         		<td>未批改的人数:</td>
	         		<td>${cjfx.num-cjfx.nnum-cjfx.nqnum }</td>
	      		</tr>
	      		<tr>
	         		<td>缺考人数:</td>
	         		<td>${cjfx.nqnum }</td>
	         		<td></td>
	         		<td></td>
	      		</tr>
	      		<tr>
	         		<td>优秀人数:</td>
	         		<td>${cjfx.nynum}</td>
	         		<td>优秀率:</td>
	         		<td>${cjfx.yx}%</td>
	      		</tr>
	      		<tr>
	         		<td>不及格人数:</td>
	         		<td>${cjfx.nbnum}</td>
	         		<td>不及格率:</td>
	         		<td>${cjfx.bjg}%</td>
	      		</tr>
			</table>





      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; wyh&lcq</p>
      </footer>

    </div><!--/.container-->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    <script src="offcanvas.js"></script>
  </body>
</html>
