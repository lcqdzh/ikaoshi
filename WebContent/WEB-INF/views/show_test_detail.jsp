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
            <a href="stu_test_list" class="list-group-item ">待参加</a>
            <a href="stu_testd_list" class="list-group-item active">已参加</a>
            <a href="not_begin_list" class="list-group-item">未开始</a>
            <a href="overdue_list" class="list-group-item ">已过期</a>
            <a href="add_test" class="list-group-item ">添加考试</a>
            <a href="test_analyse" class="list-group-item ">考试分析</a>
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
					  <div class="panel-heading">选择题</div>
					  <!-- Table -->
					  <table class="table">
					   <thead>
					      <tr>
					         <th style="text-align:center">题号</th>
					         <th style="text-align:center">题设</th>
					         <th style="text-align:center">选项1</th>
					         <th style="text-align:center">选项2</th>
					         <th style="text-align:center">选项3</th>
					         <th style="text-align:center">选项4</th>
					         <th style="text-align:center">正确选项</th>
					         <th style="text-align:center">你的选项</th>
					         <th style="text-align:center">分值</th>					     
					      </tr>
					   </thead>
					   <tbody>
					   <c:if test="${!empty dx_list}"> 
		               		<c:forEach items="${dx_list}" varStatus="sta" var="u">
					      		<tr>
					         		<td style="text-align:center">${sta.index+1}</td>
					         		<td style="text-align:center">${u.question_content}</td>
					         		<td style="text-align:center">${u.choice_A}</td>
					         		<td style="text-align:center">${u.choice_B}</td>
					         		<td style="text-align:center">${u.choice_C}</td>
					         		<td style="text-align:center">${u.choice_D}</td>
					         		<td style="text-align:center">选项${u.answer}</td>
					         		<td style="text-align:center">选项${u.stu_answer}</td>
					         		<td style="text-align:center">${u.question_score}</td>
					      		</tr>
					      	</c:forEach>
		              </c:if>
					   </tbody>
					</table>
				</div>
					
					
					
		       <div class="panel panel-default">
					  <!-- Default panel contents -->
					  <div class="panel-heading">判断题</div>
					  <!-- Table -->
					  <table class="table">
					   <thead>
					      <tr>
					         <th>题号</th>
					         <th>题设</th>
					         <th>选项1</th>
					         <th>选项2</th>					        
					         <th>正确选项</th>
					         <th>你的选项</th>
					         <th>分值</th>					     
					      </tr>
					   </thead>
					   <tbody>
					   <c:if test="${!empty pd_list}"> 
		               		<c:forEach items="${pd_list}" varStatus="sta" var="u">
					      		<tr>
					         		<td>${sta.index+1}</td>
					         		<td>${u.question_content}</td>
					         		<td>${u.choice_A}</td>
					         		<td>${u.choice_B}</td>					         		
					         		<td>选项${u.answer}</td>
					         		<td>选项${u.stu_answer}</td>
					         		<td>${u.question_score}</td>
					      		</tr>
					      	</c:forEach>
		              </c:if>
					   </tbody>
					</table>
				</div>
					
					
					
		       <div class="panel panel-default">
					  <!-- Default panel contents -->
					  <div class="panel-heading">简答题</div>
					  <!-- Table -->
					  <table class="table">
					   <thead>
					      <tr>
					         <th>题号</th>
					         <th>题设</th>					         
					         <th>你的答案</th>
					         <th>分值</th>
					         <th>得分</th>					     
					      </tr>
					   </thead>
					   <tbody>
					   <c:if test="${!empty dt_list}"> 
		               		<c:forEach items="${dt_list}" varStatus="sta" var="u">
					      		<tr>
					         		<td>${sta.index+1}</td>
					         		<td>${u.question_content}</td>					         		
					         		<td>${u.stu_answer}</td>
					         		<td>${u.question_score}</td>
					         		<td>${u.score}</td>
					      		</tr>
					      	</c:forEach>
		              </c:if>
					   </tbody>
					</table>
				</div>
				
					<button class="btn btn-primary " data-toggle="modal" data-target="#myModal">申诉</button>
					<!-- 模态框（Modal） -->
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
											&times;
										</button>
										<h4 class="modal-title" id="myModalLabel">
											ATTENTION!
										</h4>
									</div>
									<div class="modal-body">
										<p>你确定要进行申诉？</p>
										<p>此功能只能使用一次哦</p>										
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">取消
										</button>
										<button type="button" class="btn btn-primary" onclick="location.href='consult?test_Id=${test_Id}'">
											仍然申诉
										</button>
									</div>
								</div><!-- /.modal-content -->
							</div><!-- /.modal -->
						</div>
					         		
				
		       
            
          </div><!--/row-->
          
          
          
          
          
        </div><!--/.col-xs-12.col-sm-9-->

      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy;wyh&lcq</p>
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
