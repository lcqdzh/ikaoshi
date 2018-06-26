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
    <style type="text/css">

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
            <a href="tea_consult_list" class="list-group-item active">答疑功能</a>
            <a href="tea_consult_wait" class="list-group-item">等待回复</a>
            <a href="tea_consult_over" class="list-group-item">已经回复</a>
          </div>
        </div><!--/.sidebar-offcanvas-->


        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1>欢迎使用在线考试系统</h1>
            <p></p>
          </div>
          <div class="row">
       <div class="panel panel-default">
			  <!-- Default panel contents -->
			  <div class="panel-heading">答疑信息</div>
			  <!-- Table -->
			  <table class="table">
			   <thead>
			      <tr>
			         <th>考试编号</th>
			         <th>学号</th>
			         <th>姓名</th>
			         <th>问题</th>
			         <th>回复</th>
			      </tr>
			   </thead>
			   <tbody>
			   <c:if test="${!empty consult}"> 
               		<c:forEach items="${consult}" var="u">
			      		<tr>
			         		<td>${u.test_Id}</td>
			         		<td>${u.stu_Id}</td>
			         		<td>${u.stu_name}</td>
			         		<td>${u.question}</td>
			         		<td>
			         		 <c:if test="${u.state=='0'}"> 
				         		<button type="button" class="btn btn-danger" onclick="location.href='tea_consult_dg?test_id=${u.test_Id}&stu_id=${u.stu_Id }'">回复</button>
							 </c:if>
								<c:if test="${u.state=='1'}">
								已回复 
				         		</c:if>
			         		</td>
			      		</tr>
			      	</c:forEach>
              </c:if>
			   </tbody>
			</table>
			</div>
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
