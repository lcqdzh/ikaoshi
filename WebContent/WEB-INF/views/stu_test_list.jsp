<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

    <title>Off Canvas Template for Bootstrap</title>

  	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
            <li class="active"><a href="stu_home">首页</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="stu_info">修改密码</a></li>
            <li><a href="logout">注销</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
           <a href="stu_test_list" class="list-group-item active">待参加</a>
            <a href="stu_testd_list" class="list-group-item">已参加</a>
            <a href="not_begin_list" class="list-group-item">未开始</a>
            <a href="overdue_list" class="list-group-item">已过期</a>
            <a href="add_test" class="list-group-item">添加考试</a>
            <a href="test_analyse" class="list-group-item">考试分析</a>
            <a href="my_consult" class="list-group-item ">我的申诉</a>
           
          </div>
        </div><!--/.sidebar-offcanvas-->


        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="row">
          
          
          
           <div class="panel panel-default">
					  <!-- Default panel contents -->
					  <div class="panel-heading">待参加考试列表</div>
					  <!-- Table -->
					  <table class="table">
					   <thead>
					      <tr>
					         <th style="text-align:center">考试号</th>
					         <th style="text-align:center">考试名</th>
					         <th style="text-align:center">出题老师</th>
					         <th style="text-align:center">考试时长</th>
					         <th style="text-align:center">开放时间</th>
					         <th style="text-align:center">截止时间</th>	
					         <th style="text-align:center">进入</th>					        
					      </tr>
					   </thead>
					   <tbody>
					   <c:if test="${!empty stu_test_list}"> 
		               		<c:forEach items="${stu_test_list}" var="test">
					      		<tr>
					         		<td style="text-align:center">${test.test_Id}</td>
					         		<td style="text-align:center">${test.test_name }</td>
					         		<td style="text-align:center">${test.tea_name}</td>
					         		<td style="text-align:center">${test.time_long }分钟</td>
					         		<td style="text-align:center">${test.begin_Time }</td>
					         		<td style="text-align:center">${test.end_Time }</td>					 
					         		<td style="text-align:center"><button type="button" class="btn btn-danger" onclick="location.href='enter_test?test_Id=${test.test_Id}'">进入考试</button></td> 
					         	<!--<td style="text-align:center"><button class="btn btn-primary " data-toggle="modal" data-target="#myModal">进入考试</button> 
					         		
					         		
					         		
					         		</td>  -->
					         		
					         																         	
					      		</tr>
					      					
					      	</c:forEach>
		              </c:if>
					   </tbody>
					</table>
			</div>

		       
          
          
          
          
         
            
            
            
          </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

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
