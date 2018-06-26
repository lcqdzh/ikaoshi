<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>iTest</title>

<link href="css/main.css" rel="stylesheet" type="text/css" />
<link href="css/iconfont.css" rel="stylesheet" type="text/css" />
<link href="css/test.css" rel="stylesheet" type="text/css" />
<style>
.hasBeenAnswer {
	background: #5d9cec;
	color:#fff;
}
</style>

</head>
<body>
<div class="main">
	<!--nr start-->
	<div class="test_main">
		<div class=""><!-- 这里调整答题区的宽度 -->
			<div class="test">
				<form action="test_submit" id="form"  method="post">
					<div class="test_title">
						<p class="test_time">
							<i class="icon iconfont">&#xe6fb;</i><b class="alt-1" id="show_time">${hour}:${minute}</b>
						</p>
						<font><input type="button" onclick="formsubmit()"  name="test_jiaojuan" value="交卷"></font>
					</div>
					<c:if test="${question_dx_list.size()!=0}">
					<div class="test_content">
						<div class="test_content_title">
							<h2>单选题</h2>
							<p>
								<span>共</span><i class="content_lit">${question_dx_list.size()}</i><span>题，</span><span>合计</span><i class="content_fs">${question_dx_list.size()*question_dx_list.get(0).question_score}</i><span>分</span>
							</p>
						</div>
					</div>
					<div class="test_content_nr">
					<ul>
					<c:forEach items="${question_dx_list}"  varStatus="sta" var="question">
						<li id="qu_0_${sta.index} ">
									<div class="test_content_nr_tt">
										<i>${sta.index+1 }</i><span>(${question.question_score }分)</span><font>${question.question_content }</font><b class="icon iconfont">&#xe881;</b>
									</div>

									<div class="test_content_nr_main">
										<ul>
											
												<li class="option">
														<input type="hidden" name="ans${ sta.index+1}" value="${question.question_Id}">
														<input type="radio" class="radioOrCheck" name="answer${sta.index+1}" value="1"
															id="0_answer_${sta.index+1 }_option_1"
														/>
													
													
													<label for="0_answer_${sta.index+1 }_option_1">
														A.
														<p class="ue" style="display: inline;">${question.choice_A }</p>
													</label>
												</li>
											
												<li class="option">
														<input type="hidden" name="ans${ sta.index+1}" value="${question.question_Id}">
														<input type="radio" class="radioOrCheck" name="answer${sta.index+1 }" value="2"
															id="0_answer_${sta.index+1 }_option_2"
														/>
													
													
													<label for="0_answer_${sta.index+1 }_option_2">
														B.
														<p class="ue" style="display: inline;">${question.choice_B }</p>
													</label>
												</li>
											
												<li class="option">
														<input type="hidden" name="ans${ sta.index+1}" value="${question.question_Id}">
														<input type="radio" class="radioOrCheck" name="answer${sta.index+1 }" value="3"
															id="0_answer_${sta.index+1 }_option_3"
														/>
													
													
													<label for="0_answer_${sta.index+1 }_option_3">
														C.
														<p class="ue" style="display: inline;">${question.choice_C }</p>
													</label>
												</li>
											
												<li class="option">
														<input type="hidden" name="ans${ sta.index+1}" value="${question.question_Id}">
														<input type="radio" class="radioOrCheck" name="answer${sta.index+1 }" value="4"
															id="0_answer_${sta.index+1 }_option_4"
														/>
													
													
													<label for="0_answer_${sta.index+1 }_option_4">
														D.
														<p class="ue" style="display: inline;">${question.choice_D }</p>
													</label>
												</li>
											
										</ul>
									</div>
						</li>
					</c:forEach>
					</ul>
					</div>
					</c:if>
					<c:if test="${question_pd_list.size()!=0}">
					<div class="test_content">
						<div class="test_content_title">
							<h2>判断题</h2>
							<p>
								<span>共</span><i class="content_lit">${question_pd_list.size()}</i><span>题，</span><span>合计</span><i class="content_fs">${question_pd_list.size()*question_pd_list.get(0).question_score}</i><span>分</span>
							</p>
						</div>
					</div>
					<div class="test_content_nr">
					<ul>
					<c:forEach items="${question_pd_list}"  varStatus="sta" var="question">
						<li id="qu_1_${sta.index} ">
									<div class="test_content_nr_tt">
										<i>${sta.index+1 }</i><span>(${question.question_score }分)</span><font>${question.question_content }</font><b class="icon iconfont">&#xe881;</b>
									</div>

									<div class="test_content_nr_main">
										<ul>
											
												<li class="option">
														<input type="hidden" name="ans${ sta.index+1+question_dx_list.size()}" value="${question.question_Id}">
														<input type="radio" class="radioOrCheck" name="answer${sta.index+1+question_dx_list.size()}" value="1"
															id="1_answer_${sta.index+1 }_option_1"
														/>
													
													
													<label for="1_answer_${sta.index+1 }_option_1">
														A.
														<p class="ue" style="display: inline;">正确</p>
													</label>
												</li>
											
												<li class="option">
														<input type="hidden" name="ans${ sta.index+1+question_dx_list.size()}" value="${question.question_Id}">
														<input type="radio" class="radioOrCheck" name="answer${sta.index+1+question_dx_list.size() }" value="2"
															id="1_answer_${sta.index+1 }_option_2"
														/>
													
													
													<label for="1_answer_${sta.index+1 }_option_2">
														B.
														<p class="ue" style="display: inline;">错误</p>
													</label>
												</li>											
										</ul>
									</div>
						</li>
					</c:forEach>
					</ul>
					</div>
					</c:if>
					
					<c:if test="${question_dt_list.size()!=0}">
					<div class="test_content">
						<div class="test_content_title">
							<h2>简答题</h2>
							<p>
								<span>共</span><i class="content_lit">${question_dt_list.size()}</i><span>题，</span><span>合计</span><i class="content_fs">${question_dt_list.size()*question_dt_list.get(0).question_score}</i><span>分</span>
							</p>
						</div>
					</div>
					<div class="test_content_nr">
					<ul>
					<c:forEach items="${question_dt_list}"  varStatus="sta" var="question">
						<li id="qu_1_${sta.index} ">
									<div class="test_content_nr_tt">
										<i>${sta.index+1 }</i><span>(${question.question_score }分)</span><font>${question.question_content }</font><b class="icon iconfont">&#xe881;</b>
									</div>

									<div class="test_content_nr_main">
										<ul>
											
												<li class="option">
													 <input type="hidden" name="ans${ sta.index+1+question_dx_list.size()+question_pd_list.size()}" value="${question.question_Id}"> 
													<textarea    rows="6" cols="150"  name="answer${sta.index+1+question_dx_list.size()+question_pd_list.size()}"></textarea>
												</li>										
										</ul>
									</div>
						</li>
					</c:forEach>
					</ul>
					</div>
					</c:if>
					
					
				</form>
			</div>
		</div>
	</div>
	<div class="foot"></div>
</div>
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/jquery.easy-pie-chart.js"></script>
<!--时间js-->
<script src="js/jquery.countdown.js"></script>
<script>
	
console.log('控制台输出1');
var str=setInterval("clock()",1000);
var flag=0;//不是自动提交

function clock(){
	 console.log('控制台输出');
	 console.log($("#show_time").html().substr(11+$("#show_time").html().indexOf("datetime"),10));
	if($("#show_time").html().substr(11+$("#show_time").html().indexOf("datetime"),10)=="T00H00M00S"){
		//自动交卷
		flag=1;
		formsubmit();
		
		clearInterval(str);
	}
	
}
function formsubmit(){
	if(flag==0){
		if(window.confirm("你确定提交吗?")){
			document.getElementById("form").submit();
		}
	}else{
		//这里还有点问题，要是不点确定呢？
		alert("时间到！！！试卷自动提交");
		document.getElementById("form").submit();
	}
		
}

	window.jQuery(function($) {
		"use strict";
		
		$('time').countDown({
			with_separators : false
		});
		$('.alt-1').countDown({
			css_class : 'countdown-alt-1'
		});
		$('.alt-2').countDown({
			css_class : 'countdown-alt-2'
		});
		
	});
	
	
	$(function() {
		$('li.option label').click(function() {
		debugger;
			var examId = $(this).closest('.test_content_nr_main').closest('li').attr('id'); // 得到题目ID
			var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
			// 设置已答题
			if(!cardLi.hasClass('hasBeenAnswer')){
				cardLi.addClass('hasBeenAnswer');
			}
			
		});
	});
</script>
</body>
</html>
		
						