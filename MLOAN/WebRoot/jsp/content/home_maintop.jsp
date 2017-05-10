<%@ page pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
  <head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/design/css/content/home_maintop.css" />
  </head>
	<div id='home_maintop_div1' class='home_maintop_div1' style='display: none;'>
		<ul>
			<li id="ul_li_testConf"  class="hit" onclick="myFunction()">测试配量</li>
			<li id="ul_li_testControl"  onclick="myFunction()">测试控制</li>
		</ul>
	</div>
	<div id='home_maintop_div2' class='home_maintop_div2' style='display: none;'>
		<ul>
			<li class="hit" id="form2_li1"  onclick="myFunction()">文件选择</li>
			<li id="form2_li2"  onclick="myFunction()">高级设置</li>
		</ul>
	</div>
	<div id='home_maintop_div3' class='home_maintop_div3' style='display: none;'>
		<ul>
			<li class="hit"  id="ul_li_summarize"  onclick="myFunction()">数据明细</li>
			<li  id="ul_li_charts" onclick="myFunction()">图表展示</li>
			<li  id="ul_li_initReport" onclick="myFunction()">生成报告</li>
		</ul>
	</div>