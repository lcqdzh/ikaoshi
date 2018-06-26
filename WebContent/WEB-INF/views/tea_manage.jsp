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

    <title>欢迎来到在线考试系统</title>

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
            <li class="active"><a href="admin_home">首页</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="admin_update_password_page">修改密码</a></li>
            <li><a href="logout">注销</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="stu_manage" class="list-group-item " >学生管理</a>
            <a href="tea_manage" class="list-group-item active">老师管理</a>
            <a href="admin_import_stu" class="list-group-item">导入学生</a>
            <a href="admin_import_tea" class="list-group-item">导入老师</a>
            
            
          </div>
        </div><!--/.sidebar-offcanvas-->


        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>         
          <div class="row">
          
          
           <div class="panel panel-default">
					  <!-- Default panel contents -->
					  <div class="panel-heading">老师列表</div>
					  <!-- Table -->
					  <table class="table">
					   <thead>
					      <tr>
					         <th style="text-align:center">教工号</th>
					         <th style="text-align:center">姓名</th>					         
					         <th style="text-align:center">重置密码</th>
					         <th style="text-align:center">删除老师</th>					        						        					        
					      </tr>
					   </thead>
					   <tbody>
					   <c:if test="${!empty tea_list}"> 
		               		<c:forEach items="${tea_list}" var="test">
					      		<tr>
					         		<td style="text-align:center">${test.tea_ID}</td>
					         		<td style="text-align:center">${test.tea_name }</td>					         		
					         		<td style="text-align:center"><a href="admin_update_tea_password?tea_Id=${test.tea_ID}"><button type="button" class="btn btn-info" >重置密码</button></a></td>									
									<td style="text-align:center"><a href="delete_tea?tea_Id=${test.tea_ID}"><button type="button" class="btn btn-danger" >删除老师</button></a></td>  
					      			<!--  <td style="text-align:center"><button class="btn btn-danger " data-toggle="modal" data-target="#myModal">删除老师</button> -->
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
														<p>您确定要删除？</p>														
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">取消
														</button>
														<button type="button" class="btn btn-primary" onclick="location.href='delete_tea?tea_Id=${test.tea_ID}'">
															确定删除
														</button>
													</div>
												</div><!-- /.modal-content -->
											</div><!-- /.modal -->
										</div>
					         		
					         		
					         		
					         		</td>
					         		
										
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
    <script type="text/javascript">
    function del(){    	
  		if(window.confirm("你确定删除吗?")){
  			window.location.href = "delete_tea?tea_Id=${test.tea_ID}";
  		}
    		
    }
    </script>
  </body>
</html>
