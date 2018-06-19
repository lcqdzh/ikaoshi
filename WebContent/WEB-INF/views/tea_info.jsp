<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
            <li><a href="#about">我的信息</a></li>
            <li><a href="#contact">注销</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div class="container">

      <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
            <a href="#" class="list-group-item active">题库管理</a>
            <a href="#" class="list-group-item">考试管理</a>
            <a href="#" class="list-group-item">批改试卷</a>
            <a href="#" class="list-group-item">考试信息</a>
          </div>
        </div><!--/.sidebar-offcanvas-->


        <div class="col-xs-12 col-sm-9">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1>欢迎使用在线考试系统</h1>
            <p>This is an example to show the potential of an offcanvas layout pattern in Bootstrap. Try some responsive-range viewport sizes to see it in action.</p>
          </div>
          <div class="row clearfix">
                    <div class="col-md-2 column">
                    </div>
                    <div class="col-md-6 column">
                        <form action="xiugaixinxi" method = "post" role="form">
                            <div class="form-group">
                                 <label for="exampleInputEmail1">姓名</label><input class="form-control" placeholder="${stuxinxi.name }" id="exampleInputName1" type = "text" name = "name" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">院系</label><select class="form-control" id="exampleInputyuanxi1" type = "text" name = "yuanxi">
						      <option>${stuxinxi.yuanxi }</option>
						      <option>船舶与海洋工程学院</option>
						      <option>海洋科学与技术学院</option>
						      <option>理学院</option>
						      <option>土木工程系</option>
						      <option>计算机科学与技术学院</option>
						      <option>软件学院</option>
						    </select>
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">专业</label><input class="form-control" placeholder="${stuxinxi.zhuanye }"  id="exampleInputzhuanye1" type = "text" name = "zhuanye" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">班级</label><input class="form-control" placeholder="${stuxinxi.banji }"  id="exampleInputclass1" type = "text" name = "banji" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">电话</label><input class="form-control" placeholder="${stuxinxi.tel}"  id="exampleInputPhone1" type = "text" name = "tel" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">邮箱</label><input class="form-control" placeholder="${stuxinxi.mail }"  id="exampleInputMail1" type = "text" name = "mail" />
                            </div>
                            <div class="form-group">
                                 <label for="exampleInputEmail1">信誉</label><input class="form-control" placeholder="${stuxinxi.xinyu }"  id="exampleInputXinyu1" type = "text" name = "xinyu" disabled="disabled" readonly="readonly" />
                            </div>
                            <div class="row clearfix">
                                <div class="col-md-4 column">
                                    </div> </h1><input type = "submit" value = "提交" class="btn btn-primary btn-lg"  onclick="return check1();"></a>
                                </div>
                                <div class="col-md-4 column">
                                   
                                </div>
                                <div class="col-md-4 column">
                                </div>
                            </div>
                                 
                           
                        </form>
                    </div>
                    <div class="col-md-4 column">
                    </div>
                </div>
          
        </div><!--/.col-xs-12.col-sm-9-->

      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; 2016 Company, Inc.</p>
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
