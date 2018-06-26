<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>在线考试系统</title>
<!--因为我们所租用的廉价服务器性能很差，这里借用其他网站提供的bootstrap js和css以缓解服务器带宽压力-->
<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script language="javascript">

function check1(){  
    var flag = true;  
    var admin = document.getElementById("exampleInputEmail1").value;  
    var password = document.getElementById("exampleInputPassword1").value;  
    if ("" == admin){  
        alert("请输入账号！");  
        flag = false;  
        return false;  
    }  
    var len1 =admin.length;
    if(len1==9)
    {
    	for(var i=0;i<len1;i++)

        {

                      if(admin.charAt(i)>"9"|| admin.charAt(i)<"0")

                      {

                                    alert("学号/工号中含有非数字字符");
                                    flag = false;
                                    return false;

                      }

        }
    }
    else
    {
    	alert("请输入9位的学号/工号")
    	flag=false;
    	return false;
    }
    if ("" == password){  
        alert("请输入密码！");  
        flag = false;  
        return false;  
    }  
    
    
    if(flag == true){  
        //form.submit();  
        return true;  
    }  
    
}  
 

function login_fail(){
      alert("登录失败!请确定你输入的用户名和密码是否正确！");
  }
</script>

</head>
<body>
	<script>
	!
	function() {
	    function n(n, e, t) {
	        return n.getAttribute(e) || t
	    }
	    function e(n) {
	        return document.getElementsByTagName(n)
	    }
	    function t() {
	        var t = e("script"),
	        o = t.length,
	        i = t[o - 1];
	        return {
	            l: o,
	            z: n(i, "zIndex", -1),
	            o: n(i, "opacity", .5),
	            c: n(i, "color", "0,0,0"),
	            n: n(i, "count", 99)
	        }
	    }
	    function o() {
	        a = m.width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth,
	        c = m.height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight
	    }
	    function i() {
	        r.clearRect(0, 0, a, c);
	        var n, e, t, o, m, l;
	        s.forEach(function(i, x) {
	            for (i.x += i.xa, i.y += i.ya, i.xa *= i.x > a || i.x < 0 ? -1 : 1, i.ya *= i.y > c || i.y < 0 ? -1 : 1, r.fillRect(i.x - .5, i.y - .5, 1, 1), e = x + 1; e < u.length; e++) n = u[e],
	            null !== n.x && null !== n.y && (o = i.x - n.x, m = i.y - n.y, l = o * o + m * m, l < n.max && (n === y && l >= n.max / 2 && (i.x -= .03 * o, i.y -= .03 * m), t = (n.max - l) / n.max, r.beginPath(), r.lineWidth = t / 2, r.strokeStyle = "rgba(" + d.c + "," + (t + .2) + ")", r.moveTo(i.x, i.y), r.lineTo(n.x, n.y), r.stroke()))
	        }),
	        x(i)
	    }
	    var a, c, u, m = document.createElement("canvas"),
	    d = t(),
	    l = "c_n" + d.l,
	    r = m.getContext("2d"),
	    x = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
	    function(n) {
	        window.setTimeout(n, 1e3 / 45)
	    },
	    w = Math.random,
	    y = {
	        x: null,
	        y: null,
	        max: 2e4
	    };
	    m.id = l,
	    m.style.cssText = "position:fixed;top:0;left:0;z-index:" + d.z + ";opacity:" + d.o,
	    e("body")[0].appendChild(m),
	    o(),
	    window.onresize = o,
	    window.onmousemove = function(n) {
	        n = n || window.event,
	        y.x = n.clientX,
	        y.y = n.clientY
	    },
	    window.onmouseout = function() {
	        y.x = null,
	        y.y = null
	    };
	    for (var s = [], f = 0; d.n > f; f++) {
	        var h = w() * a,
	        g = w() * c,
	        v = 2 * w() - 1,
	        p = 2 * w() - 1;
	        s.push({
	            x: h,
	            y: g,
	            xa: v,
	            ya: p,
	            max: 6e3
	        })
	    }
	    u = s.concat([y]),
	    setTimeout(function() {
	        i()
	    },
	    100)
	} ();
	</script>

    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                
                <div class="jumbotron">
                    <h1 style="text-align:center">
                        欢迎使用在线考试平台</h1>
                        <h4> </h4>
                        <h2 style="text-align:center">账号或密码错误。请选择相应用户重新进行登录。</h2>
                    
                </div>
                <div class="row clearfix">
                    <div class="col-md-4 column">
                    </div>
                    <div class="col-md-6 column">
                            <div class="form-group">
                                 <button type="button" class="btn btn-success btn btn-lg" onclick="location.href='stu_login_s'">学生</button>  
                            
                                 <button type="button" class="btn btn-info btn btn-lg" onclick="location.href='tea_login_s'">教师</button>  
                                 
                                 <button type="button" class="btn btn-warning btn btn-lg" onclick="location.href='admin_login_s'">管理</button>  
                            </div>
                            <div class="form-group">
                                 
                            </div>
                            <div class="row clearfix">
                                <div class="col-md-4 column">
                                    </div> </h1></a>
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
                <div class="row clearfix">
                    <div class="col-md-4 column">
                    </div>
                    <div class="col-md-4 column">
                    </div>
                    <div class="col-md-4 column">
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-6 column">
                    </div>
                    <div class="col-md-6 column">
                    </div>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>